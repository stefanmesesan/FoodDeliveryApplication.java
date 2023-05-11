package com.example.licenta.utils;

import com.example.licenta.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static com.example.licenta.model.UserRole.*;

public class UserRoleMapper {
    private static final SimpleGrantedAuthority userGrantedAuthority = new SimpleGrantedAuthority(CUSTOMER.toString());
    private static final SimpleGrantedAuthority AdminGrantedAuthority = new SimpleGrantedAuthority(ADMIN.toString());
    private static final SimpleGrantedAuthority deliveryManagerGrantedAuthority = new SimpleGrantedAuthority(RESTAURANT_OPERATOR.toString());

    private static final SimpleGrantedAuthority administratorGrantedAuthority = new SimpleGrantedAuthority(DELIVERY_GUY.toString());

    public static UserRole convert(Collection<? extends GrantedAuthority> grantedAuthorityCollection) {
        if (grantedAuthorityCollection.contains(userGrantedAuthority))
            return CUSTOMER;
        else if (grantedAuthorityCollection.contains(AdminGrantedAuthority))
            return ADMIN;
        else if (grantedAuthorityCollection.contains(deliveryManagerGrantedAuthority))
            return RESTAURANT_OPERATOR;
        else if (grantedAuthorityCollection.contains(administratorGrantedAuthority))
            return DELIVERY_GUY;
        else return null;
    }
}
