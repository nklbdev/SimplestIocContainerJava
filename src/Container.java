import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Container implements IContainer {
    private class Binding
    {
        public IContainer.IContainerDependentResolver resolver;
        public boolean isSingleInstance;
        public Object resolvedSingleInstance;
    }

    private final Map<Object, Binding> _bindings = new HashMap<Object, Binding>();

    public IContainer bind(Object key, IContainer.IContainerDependentResolver resolver, boolean isSingleInstance)
    {
        if (key == null)
            throw new IllegalArgumentException("IoC: key cannot be null");
        if (resolver == null)
            throw new IllegalArgumentException("IoC: resolver cannot be null");
        if (_bindings.containsKey(key))
            throw new IllegalStateException("IoC: Key \"" + Objects.toString(key) + "\" is already present");

        Binding binding = new Binding();
        binding.resolver = resolver;
        binding.isSingleInstance = isSingleInstance;
        _bindings.put(key, binding);
        return this;
    }

    public IContainer bind(Object key, IContainer.IContainerDependentResolver resolver) {
        return bind(key, resolver, true);
    }

    public IContainer bind(Object key, final IIndependentResolver resolver, boolean isSingleInstance)
    {
        return bind(key, new IContainerDependentResolver() {
            @Override
            public Object resolve(IContainer container) {
                return resolver.resolve();
            }
        }, isSingleInstance);
    }

    public IContainer bind(Object key, final IIndependentResolver resolver) {
        return bind(key, resolver, true);
    }

    public <T> T resolve(Class<T> classInst, Object key)
    {
        if (key == null)
            throw new IllegalArgumentException("IoC: key cannot be null");

        if (!_bindings.containsKey(key))
            throw new IllegalStateException("IoC: Cannot resolve key \"" + Objects.toString(key) + "\"");

        Binding binding = _bindings.get(key);

        if (!binding.isSingleInstance)
            return (T) binding.resolver.resolve(this);

        if (binding.resolvedSingleInstance == null)
            binding.resolvedSingleInstance = binding.resolver.resolve(this);

        return (T) binding.resolvedSingleInstance;
    }

    public <T> T resolve(Class<T> classInst) {
        return resolve(classInst, classInst);
    }
}
