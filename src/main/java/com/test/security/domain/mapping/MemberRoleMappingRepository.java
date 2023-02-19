package com.test.security.domain.mapping;

import com.test.security.domain.role.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRoleMappingRepository extends JpaRepository<MemberRoleMapping, Long> {

}
