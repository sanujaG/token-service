package my.test.token.service.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import my.test.token.service.enums.Status;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Author Sanuja_G
 * @CreatedTime on 19-10-2021 08:32:09 PM
 */
@Data
@Entity
@Table(name = "USER_DETAIL")
public class User
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String username;

    @Column(name = "USER_KEY", nullable = false)
    private String userKey;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ATTEMPT", nullable = false)
    private int attempt;

    @Column(name = "PASSWORD_STATUS")
    @Enumerated(EnumType.STRING)
    private Status passwordStatus;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_LOGGED_DATE", length = 19)
    private Date lastLoggedDate;
}
