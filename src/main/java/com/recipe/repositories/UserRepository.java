package com.recipe.repositories;

import com.recipe.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by dough on 5/10/2017.
 */
public interface UserRepository extends JpaRepository<User, UUID> {
}
