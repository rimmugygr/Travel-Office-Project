package pl.travel360.exceptions;

public class ResourcesNotProvide  extends RuntimeException {
    public ResourcesNotProvide() {
    }

    public ResourcesNotProvide(String message) {
        super(message);
    }
}
