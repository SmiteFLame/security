package com.test.security.domain.auth

import lombok.Getter
import lombok.ToString
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import java.util.ArrayList


@Getter
@ToString
class PrincipalDetails(user: User) : UserDetails {
    private val user: User

    init {
        this.user = user
    }

    /**
     * UserDetails 구현
     * 해당 유저의 권한목록 리턴
     */
    override fun getAuthorities(): Collection<GrantedAuthority> {
        val collect: MutableCollection<GrantedAuthority> = ArrayList()
        collect.add(GrantedAuthority { user.role().toString() })
        return collect
    }

    /**
     * UserDetails 구현
     * 비밀번호를 리턴
     */
    override fun getPassword(): String {
        return user.password
    }

    /**
     * UserDetails 구현
     * PK값을 반환해준다
     */
    override fun getUsername(): String {
        return user.username
    }

    /**
     * UserDetails 구현
     * 계정 만료 여부
     * true : 만료안됨
     * false : 만료됨
     */
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    /**
     * UserDetails 구현
     * 계정 잠김 여부
     * true : 잠기지 않음
     * false : 잠김
     */
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    /**
     * UserDetails 구현
     * 계정 비밀번호 만료 여부
     * true : 만료 안됨
     * false : 만료됨
     */
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    /**
     * UserDetails 구현
     * 계정 활성화 여부
     * true : 활성화됨
     * false : 활성화 안됨
     */
    override fun isEnabled(): Boolean {
        return true
    }
}