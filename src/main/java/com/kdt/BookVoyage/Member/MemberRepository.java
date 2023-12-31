package com.kdt.BookVoyage.Member;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    @Modifying
    @Query(value = "update MemberEntity m set m.deleteFlag='Y', m.timeBaseEntity.DeletedTime=:dateTime where m.userNumber=:userNumber")
    void updateDeleteFlag(@Param("userNumber") String userNumber, @Param("dateTime") LocalDateTime dateTime);

    @Modifying
    @Transactional
    @Query(value = "update MemberEntity m set m.deleteFlag=:deleteFlag, m.timeBaseEntity.DeletedTime=:dateTime where m.userNumber=:userNumber")
    void updateUserState(@Param("userNumber") String userNumber, @Param("deleteFlag") String deleteFlag, @Param("dateTime") LocalDateTime dateTime);

    @Modifying
    @Transactional
    @Query(value = "update BookEntity b set b.remain=:remain where b.isbn13=:isbn13")
    void updateProductState(@Param("isbn13") String isbn13, @Param("remain") String remain);

    @Modifying
    @Query(value = "update MemberEntity m set m.deleteFlag='N', m.timeBaseEntity.DeletedTime=null where m.userId=:userId")
    void updateDeleteFlag1(@Param("userId") String userId);

    @Modifying
    @Query(value = "update MemberEntity m set m.password=:password where m.userNumber=:userNumber")
    void updatePassword(@Param("userNumber") String userNumber, @Param("password") String password);


    void deleteByUserNumber(String userNumber);

    @Modifying
    @Query(value ="update MemberEntity m set " +
            "m.username=:username, " +
            "m.nickname=:nickname, " +
            "m.userAddress=:userAddress, " +
            "m.userDetailAddress=:userDetailAddress, " +
            "m.gender=:gender, " +
            "m.timeBaseEntity.UpdatedTime=:updateTime, " +
            "m.userTel=:userTel " +
            "where m.userNumber=:userNumber"
            )
    void updateMyInfo(@Param("username")String username,
                      @Param("nickname")String nickname,
                      @Param("userAddress")String userAddress,
                      @Param("userDetailAddress")String userDetailAddress,
                      @Param("gender")String gender,
                      @Param("userTel")String userTel,
                      @Param("userNumber")String userNumber,
                      @Param("updateTime") LocalDateTime updateTime);

    List<MemberEntity> findByDeleteFlag(String deleteFlag);
    Optional<MemberEntity> findByUserId(String userId);
    Optional<MemberEntity> findByNickname(String nickname);
    Optional<MemberEntity> findByUserEmail(String email);
    Optional<MemberEntity> findByUserNumber(String number);
    Optional<MemberEntity> findAllByUserNumber(String userNumber);
    Optional<MemberEntity> findByUserEmailAndUserId(String userEmail, String userId);

    @EntityGraph(attributePaths = "cart")
    @Query(value = "select m from MemberEntity m")
    Page<MemberEntity> findAllMembers(Pageable pageable);

    @EntityGraph(attributePaths = "cart")
    @Query(value = "select m from MemberEntity m")
    List<MemberEntity> findAllMembers();

    @EntityGraph(attributePaths = "cart")
    Page<MemberEntity> searchByUserIdContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrNicknameContainingIgnoreCaseOrUserNumberContainingIgnoreCaseOrUserEmailContainingIgnoreCaseOrUserAddressContainingIgnoreCaseOrUserTelContainingIgnoreCase(
            String keyword1,
            String keyword2,
            String keyword3,
            String keyword4,
            String keyword5,
            String keyword6,
            String keyword7,
            Pageable pageable
    );

//    @Query(value = "select m from MemberEntity m where MemberEntity.timeBaseEntity.CreatedTime <= :startTime and MemberEntity .timeBaseEntity.CreatedTime >=:endTime")
//    Optional<List<MemberEntity>> findMembersClassifyByCreateTime(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

}
