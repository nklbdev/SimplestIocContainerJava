public class SomeClass {
    private final OtherClass _dependency;

    public SomeClass(OtherClass dependency) {
        _dependency = dependency;
    }

    public String getDependencySecret() {
        return _dependency.getSecret();
    }
}
