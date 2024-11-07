import java.util.UUID;

public class UserGenerator {

    public User random() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0,8);
        return new User("Aragorn" + uuid, "aragorn" + uuid + "@gondor.com", "p@sSword" + uuid);
    }
}
