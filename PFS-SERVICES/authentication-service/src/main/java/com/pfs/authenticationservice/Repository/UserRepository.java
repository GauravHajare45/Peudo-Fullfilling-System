package com.pfs.authenticationservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfs.authenticationservice.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserMobileNo(String userMobileNo);

}
