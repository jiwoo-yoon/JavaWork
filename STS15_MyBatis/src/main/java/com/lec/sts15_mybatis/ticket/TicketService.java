package com.lec.sts15_mybatis.ticket;

import com.lec.sts15_mybatis.board.C;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class TicketService {

    //MyBatis
    private SqlSession sqlSession;

    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    //TransactionTemplate 사용
    TransactionTemplate transactionTemplate;

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    //하나의 트랜잭션 정의
    public void buyTicket(final TicketDTO dto){
        // MyBatis 를 사용하여 이 트랜잭션 안의 에러 동작(쿼리들) 실행
        // 중간에 실패하면 트랜잭션 실패하고 자동으로 rollback 한다.
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                ITicketDAO dao = C.sqlSession.getMapper(ITicketDAO.class);
                dao.insertCard(dto.getUserId(), dto.getTicketCount());
                dao.insertTicket(dto.getUserId(), dto.getTicketCount());
            }
        });
    }
}
