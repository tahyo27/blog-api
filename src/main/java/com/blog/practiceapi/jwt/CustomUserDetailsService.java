package com.blog.practiceapi.jwt;

import com.blog.practiceapi.domain.Member;
import com.blog.practiceapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username) //email을 유저네임으로 사용
                   .orElseThrow(() -> new UsernameNotFoundException(username + "을 찾을 수 없습니다"));

        return new CustomUserDetails(member);
    }
}
