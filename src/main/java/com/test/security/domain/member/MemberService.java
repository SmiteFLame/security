package com.test.security.domain.member;

import com.test.security.domain.jwt.JwtTokenProvider;
import com.test.security.domain.mapping.MemberRoleMapping;
import com.test.security.domain.mapping.MemberRoleMappingRepository;
import com.test.security.domain.role.MemberRole;
import com.test.security.domain.role.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final MemberRoleMappingRepository memberRoleMappingRepository;

    @Transactional
    public Long join(MemberDto memberDto){
        MemberRole memberRole = memberRoleRepository.findByRoleCode("ROLE_USER").orElse(null);

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .build();


        Member newMember = memberRepository.save(member);

        memberRoleMappingRepository.save(MemberRoleMapping.builder()
                        .member(newMember)
                        .memberRole(memberRole)
                .build());

        return newMember.getMemberId();
    }

    @Transactional
    public String login(MemberDto memberDto){
        Member member = memberRepository.findByEmail(memberDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(memberDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        // 로그인에 성공하면 email, roles 로 토큰 생성 후 반환
        return jwtTokenProvider.createToken(member.getEmail(), member.getRoles());
    }
}
