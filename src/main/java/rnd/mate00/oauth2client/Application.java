package rnd.mate00.oauth2client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import rnd.mate00.oauth2client.business.PurchaseOrder;
import rnd.mate00.oauth2client.business.repository.PurchaseOrderRepository;
import rnd.mate00.oauth2client.provider.OAuth2Provider;
import rnd.mate00.oauth2client.user.DbUser;
import rnd.mate00.oauth2client.user.repository.UserRepository;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadProducts() {
        ResourceDatabasePopulator dbPopulator =
                new ResourceDatabasePopulator(new ClassPathResource("insert-users.sql"));
//        dbPopulator.execute(dataSource); // not using it at the moment because of sequence issue

        insertUsers();

        PurchaseOrder po1 = new PurchaseOrder();
        po1.setProductName("prod1");
        po1.setUser(userRepository.findByEmailAndProvider("my.email@mail.com", OAuth2Provider.GOOGLE).get());
        purchaseOrderRepository.save(po1);

    }

    private void insertUsers() {
        DbUser user = new DbUser();
        user.setName("my name");
        user.setEmail("my.email@mail.com");
        user.setProvider(OAuth2Provider.GOOGLE);

        userRepository.save(user);

        DbUser localUser = new DbUser();
        localUser.setName("John Doe");
        localUser.setEmail("sample@mail.com");
        localUser.setPassword("{bcrypt}$2a$10$1OkhA3muZDexKCo.d0LrDO8s08WRUeXMeeAR6OGGRCs5fBFUtDO8S");
        localUser.setProvider(OAuth2Provider.EMAIL);

        userRepository.save(localUser);
    }
}
