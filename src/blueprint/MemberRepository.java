package blueprint;

import entity.Member;

public interface MemberRepository {
    void addMember(Member member);
    void updateMember(Member member);
    void deleteMember(Member member);
}
