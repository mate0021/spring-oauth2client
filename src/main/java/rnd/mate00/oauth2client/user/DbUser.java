package rnd.mate00.oauth2client.user;

import lombok.Data;
import rnd.mate00.oauth2client.provider.OAuth2Provider;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER")
@Data
public class DbUser {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private OAuth2Provider provider;

    @Column
    private LocalDateTime lastLogin;
}
