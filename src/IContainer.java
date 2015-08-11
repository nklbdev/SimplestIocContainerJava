public interface IContainer {
    public interface IIndependentResolver {
        Object resolve();
    }
    public interface IContainerDependentResolver {
        Object resolve(IContainer container);
    }

    IContainer bind(Object key, IContainerDependentResolver resolver, boolean isSingleInstance);
    IContainer bind(Object key, IContainerDependentResolver resolver);
    IContainer bind(Object key, IIndependentResolver resolver, boolean isSingleInstance);
    IContainer bind(Object key, IIndependentResolver resolver);
    <T> T resolve(Class<T> classInst, Object key);
    <T> T resolve(Class<T> classInst);
}
