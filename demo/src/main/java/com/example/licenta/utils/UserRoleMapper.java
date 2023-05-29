package com.example.licenta.utils;

import com.example.licenta.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static com.example.licenta.model.UserRole.*;

public class UserRoleMapper {
    private static final SimpleGrantedAuthority customerGrantedAuthority = new SimpleGrantedAuthority(CUSTOMER.toString());
    private static final SimpleGrantedAuthority adminGrantedAuthority = new SimpleGrantedAuthority(ADMIN.toString());
    private static final SimpleGrantedAuthority deliveryGuyGrantedAuthority = new SimpleGrantedAuthority(DELIVERY_GUY.toString());

    private static final SimpleGrantedAuthority restaurantOperatorGrantedAuthority = new SimpleGrantedAuthority(RESTAURANT_OPERATOR.toString());
    public static UserRole convert(Collection<? extends GrantedAuthority> grantedAuthorityCollection) {
        if (grantedAuthorityCollection.contains(customerGrantedAuthority))
            return CUSTOMER;
        else if (grantedAuthorityCollection.contains(adminGrantedAuthority))
            return ADMIN;
        else if (grantedAuthorityCollection.contains(deliveryGuyGrantedAuthority))
            return DELIVERY_GUY;
        else if (grantedAuthorityCollection.contains(restaurantOperatorGrantedAuthority))
            return RESTAURANT_OPERATOR;
        else return null;
    }
}
