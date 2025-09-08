package com.sophocles.accounts.repository;

import com.sophocles.accounts.entity.Accounts;
import com.sophocles.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}
