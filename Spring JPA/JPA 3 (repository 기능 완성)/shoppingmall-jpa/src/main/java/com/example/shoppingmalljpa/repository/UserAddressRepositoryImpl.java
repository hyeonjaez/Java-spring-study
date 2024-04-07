package com.example.shoppingmalljpa.repository;

import com.example.shoppingmalljpa.entity.QUserAddress;
import com.example.shoppingmalljpa.entity.User;
import com.example.shoppingmalljpa.entity.UserAddress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserAddressRepositoryImpl extends QuerydslRepositorySupport implements UserAddressRepository {

    public UserAddressRepositoryImpl() {
        super(UserAddress.class);
    }
    @Override
    public List<UserAddress> findAllByUser(User user) {
        QUserAddress userAddress = QUserAddress.userAddress;

        return from(userAddress)
                .where(userAddress.user.eq(user))
                .fetch();
    }

    @Override
    public Optional<UserAddress> findUserAddressByPk_AddressIdAndPk_UserId(Long addressId, String userId) {
        QUserAddress userAddress = QUserAddress.userAddress;
        return Optional.ofNullable(
                from(userAddress)
                        .where(userAddress.pk.addressId.eq(addressId).and(userAddress.pk.userId.eq(userId)))
                        .fetchOne());
    }

    @Override
    public void deleteByPk_AddressIdAndPk_UserId(Long addressId, String userId) {
        QUserAddress userAddress = QUserAddress.userAddress;

        delete(userAddress)
                .where(userAddress.pk.addressId.eq(addressId).and(userAddress.pk.userId.eq(userId)))
                .execute();
    }
}