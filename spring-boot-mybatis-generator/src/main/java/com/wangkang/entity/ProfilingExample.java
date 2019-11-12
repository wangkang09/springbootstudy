package com.wangkang.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProfilingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProfilingExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andQueryIdIsNull() {
            addCriterion("QUERY_ID is null");
            return (Criteria) this;
        }

        public Criteria andQueryIdIsNotNull() {
            addCriterion("QUERY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQueryIdEqualTo(Integer value) {
            addCriterion("QUERY_ID =", value, "queryId");
            return (Criteria) this;
        }

        public Criteria andQueryIdNotEqualTo(Integer value) {
            addCriterion("QUERY_ID <>", value, "queryId");
            return (Criteria) this;
        }

        public Criteria andQueryIdGreaterThan(Integer value) {
            addCriterion("QUERY_ID >", value, "queryId");
            return (Criteria) this;
        }

        public Criteria andQueryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("QUERY_ID >=", value, "queryId");
            return (Criteria) this;
        }

        public Criteria andQueryIdLessThan(Integer value) {
            addCriterion("QUERY_ID <", value, "queryId");
            return (Criteria) this;
        }

        public Criteria andQueryIdLessThanOrEqualTo(Integer value) {
            addCriterion("QUERY_ID <=", value, "queryId");
            return (Criteria) this;
        }

        public Criteria andQueryIdIn(List<Integer> values) {
            addCriterion("QUERY_ID in", values, "queryId");
            return (Criteria) this;
        }

        public Criteria andQueryIdNotIn(List<Integer> values) {
            addCriterion("QUERY_ID not in", values, "queryId");
            return (Criteria) this;
        }

        public Criteria andQueryIdBetween(Integer value1, Integer value2) {
            addCriterion("QUERY_ID between", value1, value2, "queryId");
            return (Criteria) this;
        }

        public Criteria andQueryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("QUERY_ID not between", value1, value2, "queryId");
            return (Criteria) this;
        }

        public Criteria andSeqIsNull() {
            addCriterion("SEQ is null");
            return (Criteria) this;
        }

        public Criteria andSeqIsNotNull() {
            addCriterion("SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andSeqEqualTo(Integer value) {
            addCriterion("SEQ =", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotEqualTo(Integer value) {
            addCriterion("SEQ <>", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThan(Integer value) {
            addCriterion("SEQ >", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("SEQ >=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThan(Integer value) {
            addCriterion("SEQ <", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThanOrEqualTo(Integer value) {
            addCriterion("SEQ <=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqIn(List<Integer> values) {
            addCriterion("SEQ in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotIn(List<Integer> values) {
            addCriterion("SEQ not in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqBetween(Integer value1, Integer value2) {
            addCriterion("SEQ between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("SEQ not between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("STATE is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("STATE like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("STATE not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("STATE not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("DURATION is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("DURATION is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(BigDecimal value) {
            addCriterion("DURATION =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(BigDecimal value) {
            addCriterion("DURATION <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(BigDecimal value) {
            addCriterion("DURATION >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DURATION >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(BigDecimal value) {
            addCriterion("DURATION <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DURATION <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<BigDecimal> values) {
            addCriterion("DURATION in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<BigDecimal> values) {
            addCriterion("DURATION not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DURATION between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DURATION not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andCpuUserIsNull() {
            addCriterion("CPU_USER is null");
            return (Criteria) this;
        }

        public Criteria andCpuUserIsNotNull() {
            addCriterion("CPU_USER is not null");
            return (Criteria) this;
        }

        public Criteria andCpuUserEqualTo(BigDecimal value) {
            addCriterion("CPU_USER =", value, "cpuUser");
            return (Criteria) this;
        }

        public Criteria andCpuUserNotEqualTo(BigDecimal value) {
            addCriterion("CPU_USER <>", value, "cpuUser");
            return (Criteria) this;
        }

        public Criteria andCpuUserGreaterThan(BigDecimal value) {
            addCriterion("CPU_USER >", value, "cpuUser");
            return (Criteria) this;
        }

        public Criteria andCpuUserGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CPU_USER >=", value, "cpuUser");
            return (Criteria) this;
        }

        public Criteria andCpuUserLessThan(BigDecimal value) {
            addCriterion("CPU_USER <", value, "cpuUser");
            return (Criteria) this;
        }

        public Criteria andCpuUserLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CPU_USER <=", value, "cpuUser");
            return (Criteria) this;
        }

        public Criteria andCpuUserIn(List<BigDecimal> values) {
            addCriterion("CPU_USER in", values, "cpuUser");
            return (Criteria) this;
        }

        public Criteria andCpuUserNotIn(List<BigDecimal> values) {
            addCriterion("CPU_USER not in", values, "cpuUser");
            return (Criteria) this;
        }

        public Criteria andCpuUserBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CPU_USER between", value1, value2, "cpuUser");
            return (Criteria) this;
        }

        public Criteria andCpuUserNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CPU_USER not between", value1, value2, "cpuUser");
            return (Criteria) this;
        }

        public Criteria andCpuSystemIsNull() {
            addCriterion("CPU_SYSTEM is null");
            return (Criteria) this;
        }

        public Criteria andCpuSystemIsNotNull() {
            addCriterion("CPU_SYSTEM is not null");
            return (Criteria) this;
        }

        public Criteria andCpuSystemEqualTo(BigDecimal value) {
            addCriterion("CPU_SYSTEM =", value, "cpuSystem");
            return (Criteria) this;
        }

        public Criteria andCpuSystemNotEqualTo(BigDecimal value) {
            addCriterion("CPU_SYSTEM <>", value, "cpuSystem");
            return (Criteria) this;
        }

        public Criteria andCpuSystemGreaterThan(BigDecimal value) {
            addCriterion("CPU_SYSTEM >", value, "cpuSystem");
            return (Criteria) this;
        }

        public Criteria andCpuSystemGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CPU_SYSTEM >=", value, "cpuSystem");
            return (Criteria) this;
        }

        public Criteria andCpuSystemLessThan(BigDecimal value) {
            addCriterion("CPU_SYSTEM <", value, "cpuSystem");
            return (Criteria) this;
        }

        public Criteria andCpuSystemLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CPU_SYSTEM <=", value, "cpuSystem");
            return (Criteria) this;
        }

        public Criteria andCpuSystemIn(List<BigDecimal> values) {
            addCriterion("CPU_SYSTEM in", values, "cpuSystem");
            return (Criteria) this;
        }

        public Criteria andCpuSystemNotIn(List<BigDecimal> values) {
            addCriterion("CPU_SYSTEM not in", values, "cpuSystem");
            return (Criteria) this;
        }

        public Criteria andCpuSystemBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CPU_SYSTEM between", value1, value2, "cpuSystem");
            return (Criteria) this;
        }

        public Criteria andCpuSystemNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CPU_SYSTEM not between", value1, value2, "cpuSystem");
            return (Criteria) this;
        }

        public Criteria andContextVoluntaryIsNull() {
            addCriterion("CONTEXT_VOLUNTARY is null");
            return (Criteria) this;
        }

        public Criteria andContextVoluntaryIsNotNull() {
            addCriterion("CONTEXT_VOLUNTARY is not null");
            return (Criteria) this;
        }

        public Criteria andContextVoluntaryEqualTo(Integer value) {
            addCriterion("CONTEXT_VOLUNTARY =", value, "contextVoluntary");
            return (Criteria) this;
        }

        public Criteria andContextVoluntaryNotEqualTo(Integer value) {
            addCriterion("CONTEXT_VOLUNTARY <>", value, "contextVoluntary");
            return (Criteria) this;
        }

        public Criteria andContextVoluntaryGreaterThan(Integer value) {
            addCriterion("CONTEXT_VOLUNTARY >", value, "contextVoluntary");
            return (Criteria) this;
        }

        public Criteria andContextVoluntaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("CONTEXT_VOLUNTARY >=", value, "contextVoluntary");
            return (Criteria) this;
        }

        public Criteria andContextVoluntaryLessThan(Integer value) {
            addCriterion("CONTEXT_VOLUNTARY <", value, "contextVoluntary");
            return (Criteria) this;
        }

        public Criteria andContextVoluntaryLessThanOrEqualTo(Integer value) {
            addCriterion("CONTEXT_VOLUNTARY <=", value, "contextVoluntary");
            return (Criteria) this;
        }

        public Criteria andContextVoluntaryIn(List<Integer> values) {
            addCriterion("CONTEXT_VOLUNTARY in", values, "contextVoluntary");
            return (Criteria) this;
        }

        public Criteria andContextVoluntaryNotIn(List<Integer> values) {
            addCriterion("CONTEXT_VOLUNTARY not in", values, "contextVoluntary");
            return (Criteria) this;
        }

        public Criteria andContextVoluntaryBetween(Integer value1, Integer value2) {
            addCriterion("CONTEXT_VOLUNTARY between", value1, value2, "contextVoluntary");
            return (Criteria) this;
        }

        public Criteria andContextVoluntaryNotBetween(Integer value1, Integer value2) {
            addCriterion("CONTEXT_VOLUNTARY not between", value1, value2, "contextVoluntary");
            return (Criteria) this;
        }

        public Criteria andContextInvoluntaryIsNull() {
            addCriterion("CONTEXT_INVOLUNTARY is null");
            return (Criteria) this;
        }

        public Criteria andContextInvoluntaryIsNotNull() {
            addCriterion("CONTEXT_INVOLUNTARY is not null");
            return (Criteria) this;
        }

        public Criteria andContextInvoluntaryEqualTo(Integer value) {
            addCriterion("CONTEXT_INVOLUNTARY =", value, "contextInvoluntary");
            return (Criteria) this;
        }

        public Criteria andContextInvoluntaryNotEqualTo(Integer value) {
            addCriterion("CONTEXT_INVOLUNTARY <>", value, "contextInvoluntary");
            return (Criteria) this;
        }

        public Criteria andContextInvoluntaryGreaterThan(Integer value) {
            addCriterion("CONTEXT_INVOLUNTARY >", value, "contextInvoluntary");
            return (Criteria) this;
        }

        public Criteria andContextInvoluntaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("CONTEXT_INVOLUNTARY >=", value, "contextInvoluntary");
            return (Criteria) this;
        }

        public Criteria andContextInvoluntaryLessThan(Integer value) {
            addCriterion("CONTEXT_INVOLUNTARY <", value, "contextInvoluntary");
            return (Criteria) this;
        }

        public Criteria andContextInvoluntaryLessThanOrEqualTo(Integer value) {
            addCriterion("CONTEXT_INVOLUNTARY <=", value, "contextInvoluntary");
            return (Criteria) this;
        }

        public Criteria andContextInvoluntaryIn(List<Integer> values) {
            addCriterion("CONTEXT_INVOLUNTARY in", values, "contextInvoluntary");
            return (Criteria) this;
        }

        public Criteria andContextInvoluntaryNotIn(List<Integer> values) {
            addCriterion("CONTEXT_INVOLUNTARY not in", values, "contextInvoluntary");
            return (Criteria) this;
        }

        public Criteria andContextInvoluntaryBetween(Integer value1, Integer value2) {
            addCriterion("CONTEXT_INVOLUNTARY between", value1, value2, "contextInvoluntary");
            return (Criteria) this;
        }

        public Criteria andContextInvoluntaryNotBetween(Integer value1, Integer value2) {
            addCriterion("CONTEXT_INVOLUNTARY not between", value1, value2, "contextInvoluntary");
            return (Criteria) this;
        }

        public Criteria andBlockOpsInIsNull() {
            addCriterion("BLOCK_OPS_IN is null");
            return (Criteria) this;
        }

        public Criteria andBlockOpsInIsNotNull() {
            addCriterion("BLOCK_OPS_IN is not null");
            return (Criteria) this;
        }

        public Criteria andBlockOpsInEqualTo(Integer value) {
            addCriterion("BLOCK_OPS_IN =", value, "blockOpsIn");
            return (Criteria) this;
        }

        public Criteria andBlockOpsInNotEqualTo(Integer value) {
            addCriterion("BLOCK_OPS_IN <>", value, "blockOpsIn");
            return (Criteria) this;
        }

        public Criteria andBlockOpsInGreaterThan(Integer value) {
            addCriterion("BLOCK_OPS_IN >", value, "blockOpsIn");
            return (Criteria) this;
        }

        public Criteria andBlockOpsInGreaterThanOrEqualTo(Integer value) {
            addCriterion("BLOCK_OPS_IN >=", value, "blockOpsIn");
            return (Criteria) this;
        }

        public Criteria andBlockOpsInLessThan(Integer value) {
            addCriterion("BLOCK_OPS_IN <", value, "blockOpsIn");
            return (Criteria) this;
        }

        public Criteria andBlockOpsInLessThanOrEqualTo(Integer value) {
            addCriterion("BLOCK_OPS_IN <=", value, "blockOpsIn");
            return (Criteria) this;
        }

        public Criteria andBlockOpsInIn(List<Integer> values) {
            addCriterion("BLOCK_OPS_IN in", values, "blockOpsIn");
            return (Criteria) this;
        }

        public Criteria andBlockOpsInNotIn(List<Integer> values) {
            addCriterion("BLOCK_OPS_IN not in", values, "blockOpsIn");
            return (Criteria) this;
        }

        public Criteria andBlockOpsInBetween(Integer value1, Integer value2) {
            addCriterion("BLOCK_OPS_IN between", value1, value2, "blockOpsIn");
            return (Criteria) this;
        }

        public Criteria andBlockOpsInNotBetween(Integer value1, Integer value2) {
            addCriterion("BLOCK_OPS_IN not between", value1, value2, "blockOpsIn");
            return (Criteria) this;
        }

        public Criteria andBlockOpsOutIsNull() {
            addCriterion("BLOCK_OPS_OUT is null");
            return (Criteria) this;
        }

        public Criteria andBlockOpsOutIsNotNull() {
            addCriterion("BLOCK_OPS_OUT is not null");
            return (Criteria) this;
        }

        public Criteria andBlockOpsOutEqualTo(Integer value) {
            addCriterion("BLOCK_OPS_OUT =", value, "blockOpsOut");
            return (Criteria) this;
        }

        public Criteria andBlockOpsOutNotEqualTo(Integer value) {
            addCriterion("BLOCK_OPS_OUT <>", value, "blockOpsOut");
            return (Criteria) this;
        }

        public Criteria andBlockOpsOutGreaterThan(Integer value) {
            addCriterion("BLOCK_OPS_OUT >", value, "blockOpsOut");
            return (Criteria) this;
        }

        public Criteria andBlockOpsOutGreaterThanOrEqualTo(Integer value) {
            addCriterion("BLOCK_OPS_OUT >=", value, "blockOpsOut");
            return (Criteria) this;
        }

        public Criteria andBlockOpsOutLessThan(Integer value) {
            addCriterion("BLOCK_OPS_OUT <", value, "blockOpsOut");
            return (Criteria) this;
        }

        public Criteria andBlockOpsOutLessThanOrEqualTo(Integer value) {
            addCriterion("BLOCK_OPS_OUT <=", value, "blockOpsOut");
            return (Criteria) this;
        }

        public Criteria andBlockOpsOutIn(List<Integer> values) {
            addCriterion("BLOCK_OPS_OUT in", values, "blockOpsOut");
            return (Criteria) this;
        }

        public Criteria andBlockOpsOutNotIn(List<Integer> values) {
            addCriterion("BLOCK_OPS_OUT not in", values, "blockOpsOut");
            return (Criteria) this;
        }

        public Criteria andBlockOpsOutBetween(Integer value1, Integer value2) {
            addCriterion("BLOCK_OPS_OUT between", value1, value2, "blockOpsOut");
            return (Criteria) this;
        }

        public Criteria andBlockOpsOutNotBetween(Integer value1, Integer value2) {
            addCriterion("BLOCK_OPS_OUT not between", value1, value2, "blockOpsOut");
            return (Criteria) this;
        }

        public Criteria andMessagesSentIsNull() {
            addCriterion("MESSAGES_SENT is null");
            return (Criteria) this;
        }

        public Criteria andMessagesSentIsNotNull() {
            addCriterion("MESSAGES_SENT is not null");
            return (Criteria) this;
        }

        public Criteria andMessagesSentEqualTo(Integer value) {
            addCriterion("MESSAGES_SENT =", value, "messagesSent");
            return (Criteria) this;
        }

        public Criteria andMessagesSentNotEqualTo(Integer value) {
            addCriterion("MESSAGES_SENT <>", value, "messagesSent");
            return (Criteria) this;
        }

        public Criteria andMessagesSentGreaterThan(Integer value) {
            addCriterion("MESSAGES_SENT >", value, "messagesSent");
            return (Criteria) this;
        }

        public Criteria andMessagesSentGreaterThanOrEqualTo(Integer value) {
            addCriterion("MESSAGES_SENT >=", value, "messagesSent");
            return (Criteria) this;
        }

        public Criteria andMessagesSentLessThan(Integer value) {
            addCriterion("MESSAGES_SENT <", value, "messagesSent");
            return (Criteria) this;
        }

        public Criteria andMessagesSentLessThanOrEqualTo(Integer value) {
            addCriterion("MESSAGES_SENT <=", value, "messagesSent");
            return (Criteria) this;
        }

        public Criteria andMessagesSentIn(List<Integer> values) {
            addCriterion("MESSAGES_SENT in", values, "messagesSent");
            return (Criteria) this;
        }

        public Criteria andMessagesSentNotIn(List<Integer> values) {
            addCriterion("MESSAGES_SENT not in", values, "messagesSent");
            return (Criteria) this;
        }

        public Criteria andMessagesSentBetween(Integer value1, Integer value2) {
            addCriterion("MESSAGES_SENT between", value1, value2, "messagesSent");
            return (Criteria) this;
        }

        public Criteria andMessagesSentNotBetween(Integer value1, Integer value2) {
            addCriterion("MESSAGES_SENT not between", value1, value2, "messagesSent");
            return (Criteria) this;
        }

        public Criteria andMessagesReceivedIsNull() {
            addCriterion("MESSAGES_RECEIVED is null");
            return (Criteria) this;
        }

        public Criteria andMessagesReceivedIsNotNull() {
            addCriterion("MESSAGES_RECEIVED is not null");
            return (Criteria) this;
        }

        public Criteria andMessagesReceivedEqualTo(Integer value) {
            addCriterion("MESSAGES_RECEIVED =", value, "messagesReceived");
            return (Criteria) this;
        }

        public Criteria andMessagesReceivedNotEqualTo(Integer value) {
            addCriterion("MESSAGES_RECEIVED <>", value, "messagesReceived");
            return (Criteria) this;
        }

        public Criteria andMessagesReceivedGreaterThan(Integer value) {
            addCriterion("MESSAGES_RECEIVED >", value, "messagesReceived");
            return (Criteria) this;
        }

        public Criteria andMessagesReceivedGreaterThanOrEqualTo(Integer value) {
            addCriterion("MESSAGES_RECEIVED >=", value, "messagesReceived");
            return (Criteria) this;
        }

        public Criteria andMessagesReceivedLessThan(Integer value) {
            addCriterion("MESSAGES_RECEIVED <", value, "messagesReceived");
            return (Criteria) this;
        }

        public Criteria andMessagesReceivedLessThanOrEqualTo(Integer value) {
            addCriterion("MESSAGES_RECEIVED <=", value, "messagesReceived");
            return (Criteria) this;
        }

        public Criteria andMessagesReceivedIn(List<Integer> values) {
            addCriterion("MESSAGES_RECEIVED in", values, "messagesReceived");
            return (Criteria) this;
        }

        public Criteria andMessagesReceivedNotIn(List<Integer> values) {
            addCriterion("MESSAGES_RECEIVED not in", values, "messagesReceived");
            return (Criteria) this;
        }

        public Criteria andMessagesReceivedBetween(Integer value1, Integer value2) {
            addCriterion("MESSAGES_RECEIVED between", value1, value2, "messagesReceived");
            return (Criteria) this;
        }

        public Criteria andMessagesReceivedNotBetween(Integer value1, Integer value2) {
            addCriterion("MESSAGES_RECEIVED not between", value1, value2, "messagesReceived");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMajorIsNull() {
            addCriterion("PAGE_FAULTS_MAJOR is null");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMajorIsNotNull() {
            addCriterion("PAGE_FAULTS_MAJOR is not null");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMajorEqualTo(Integer value) {
            addCriterion("PAGE_FAULTS_MAJOR =", value, "pageFaultsMajor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMajorNotEqualTo(Integer value) {
            addCriterion("PAGE_FAULTS_MAJOR <>", value, "pageFaultsMajor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMajorGreaterThan(Integer value) {
            addCriterion("PAGE_FAULTS_MAJOR >", value, "pageFaultsMajor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMajorGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAGE_FAULTS_MAJOR >=", value, "pageFaultsMajor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMajorLessThan(Integer value) {
            addCriterion("PAGE_FAULTS_MAJOR <", value, "pageFaultsMajor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMajorLessThanOrEqualTo(Integer value) {
            addCriterion("PAGE_FAULTS_MAJOR <=", value, "pageFaultsMajor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMajorIn(List<Integer> values) {
            addCriterion("PAGE_FAULTS_MAJOR in", values, "pageFaultsMajor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMajorNotIn(List<Integer> values) {
            addCriterion("PAGE_FAULTS_MAJOR not in", values, "pageFaultsMajor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMajorBetween(Integer value1, Integer value2) {
            addCriterion("PAGE_FAULTS_MAJOR between", value1, value2, "pageFaultsMajor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMajorNotBetween(Integer value1, Integer value2) {
            addCriterion("PAGE_FAULTS_MAJOR not between", value1, value2, "pageFaultsMajor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMinorIsNull() {
            addCriterion("PAGE_FAULTS_MINOR is null");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMinorIsNotNull() {
            addCriterion("PAGE_FAULTS_MINOR is not null");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMinorEqualTo(Integer value) {
            addCriterion("PAGE_FAULTS_MINOR =", value, "pageFaultsMinor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMinorNotEqualTo(Integer value) {
            addCriterion("PAGE_FAULTS_MINOR <>", value, "pageFaultsMinor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMinorGreaterThan(Integer value) {
            addCriterion("PAGE_FAULTS_MINOR >", value, "pageFaultsMinor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMinorGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAGE_FAULTS_MINOR >=", value, "pageFaultsMinor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMinorLessThan(Integer value) {
            addCriterion("PAGE_FAULTS_MINOR <", value, "pageFaultsMinor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMinorLessThanOrEqualTo(Integer value) {
            addCriterion("PAGE_FAULTS_MINOR <=", value, "pageFaultsMinor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMinorIn(List<Integer> values) {
            addCriterion("PAGE_FAULTS_MINOR in", values, "pageFaultsMinor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMinorNotIn(List<Integer> values) {
            addCriterion("PAGE_FAULTS_MINOR not in", values, "pageFaultsMinor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMinorBetween(Integer value1, Integer value2) {
            addCriterion("PAGE_FAULTS_MINOR between", value1, value2, "pageFaultsMinor");
            return (Criteria) this;
        }

        public Criteria andPageFaultsMinorNotBetween(Integer value1, Integer value2) {
            addCriterion("PAGE_FAULTS_MINOR not between", value1, value2, "pageFaultsMinor");
            return (Criteria) this;
        }

        public Criteria andSwapsIsNull() {
            addCriterion("SWAPS is null");
            return (Criteria) this;
        }

        public Criteria andSwapsIsNotNull() {
            addCriterion("SWAPS is not null");
            return (Criteria) this;
        }

        public Criteria andSwapsEqualTo(Integer value) {
            addCriterion("SWAPS =", value, "swaps");
            return (Criteria) this;
        }

        public Criteria andSwapsNotEqualTo(Integer value) {
            addCriterion("SWAPS <>", value, "swaps");
            return (Criteria) this;
        }

        public Criteria andSwapsGreaterThan(Integer value) {
            addCriterion("SWAPS >", value, "swaps");
            return (Criteria) this;
        }

        public Criteria andSwapsGreaterThanOrEqualTo(Integer value) {
            addCriterion("SWAPS >=", value, "swaps");
            return (Criteria) this;
        }

        public Criteria andSwapsLessThan(Integer value) {
            addCriterion("SWAPS <", value, "swaps");
            return (Criteria) this;
        }

        public Criteria andSwapsLessThanOrEqualTo(Integer value) {
            addCriterion("SWAPS <=", value, "swaps");
            return (Criteria) this;
        }

        public Criteria andSwapsIn(List<Integer> values) {
            addCriterion("SWAPS in", values, "swaps");
            return (Criteria) this;
        }

        public Criteria andSwapsNotIn(List<Integer> values) {
            addCriterion("SWAPS not in", values, "swaps");
            return (Criteria) this;
        }

        public Criteria andSwapsBetween(Integer value1, Integer value2) {
            addCriterion("SWAPS between", value1, value2, "swaps");
            return (Criteria) this;
        }

        public Criteria andSwapsNotBetween(Integer value1, Integer value2) {
            addCriterion("SWAPS not between", value1, value2, "swaps");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionIsNull() {
            addCriterion("SOURCE_FUNCTION is null");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionIsNotNull() {
            addCriterion("SOURCE_FUNCTION is not null");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionEqualTo(String value) {
            addCriterion("SOURCE_FUNCTION =", value, "sourceFunction");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionNotEqualTo(String value) {
            addCriterion("SOURCE_FUNCTION <>", value, "sourceFunction");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionGreaterThan(String value) {
            addCriterion("SOURCE_FUNCTION >", value, "sourceFunction");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE_FUNCTION >=", value, "sourceFunction");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionLessThan(String value) {
            addCriterion("SOURCE_FUNCTION <", value, "sourceFunction");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionLessThanOrEqualTo(String value) {
            addCriterion("SOURCE_FUNCTION <=", value, "sourceFunction");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionLike(String value) {
            addCriterion("SOURCE_FUNCTION like", value, "sourceFunction");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionNotLike(String value) {
            addCriterion("SOURCE_FUNCTION not like", value, "sourceFunction");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionIn(List<String> values) {
            addCriterion("SOURCE_FUNCTION in", values, "sourceFunction");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionNotIn(List<String> values) {
            addCriterion("SOURCE_FUNCTION not in", values, "sourceFunction");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionBetween(String value1, String value2) {
            addCriterion("SOURCE_FUNCTION between", value1, value2, "sourceFunction");
            return (Criteria) this;
        }

        public Criteria andSourceFunctionNotBetween(String value1, String value2) {
            addCriterion("SOURCE_FUNCTION not between", value1, value2, "sourceFunction");
            return (Criteria) this;
        }

        public Criteria andSourceFileIsNull() {
            addCriterion("SOURCE_FILE is null");
            return (Criteria) this;
        }

        public Criteria andSourceFileIsNotNull() {
            addCriterion("SOURCE_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceFileEqualTo(String value) {
            addCriterion("SOURCE_FILE =", value, "sourceFile");
            return (Criteria) this;
        }

        public Criteria andSourceFileNotEqualTo(String value) {
            addCriterion("SOURCE_FILE <>", value, "sourceFile");
            return (Criteria) this;
        }

        public Criteria andSourceFileGreaterThan(String value) {
            addCriterion("SOURCE_FILE >", value, "sourceFile");
            return (Criteria) this;
        }

        public Criteria andSourceFileGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE_FILE >=", value, "sourceFile");
            return (Criteria) this;
        }

        public Criteria andSourceFileLessThan(String value) {
            addCriterion("SOURCE_FILE <", value, "sourceFile");
            return (Criteria) this;
        }

        public Criteria andSourceFileLessThanOrEqualTo(String value) {
            addCriterion("SOURCE_FILE <=", value, "sourceFile");
            return (Criteria) this;
        }

        public Criteria andSourceFileLike(String value) {
            addCriterion("SOURCE_FILE like", value, "sourceFile");
            return (Criteria) this;
        }

        public Criteria andSourceFileNotLike(String value) {
            addCriterion("SOURCE_FILE not like", value, "sourceFile");
            return (Criteria) this;
        }

        public Criteria andSourceFileIn(List<String> values) {
            addCriterion("SOURCE_FILE in", values, "sourceFile");
            return (Criteria) this;
        }

        public Criteria andSourceFileNotIn(List<String> values) {
            addCriterion("SOURCE_FILE not in", values, "sourceFile");
            return (Criteria) this;
        }

        public Criteria andSourceFileBetween(String value1, String value2) {
            addCriterion("SOURCE_FILE between", value1, value2, "sourceFile");
            return (Criteria) this;
        }

        public Criteria andSourceFileNotBetween(String value1, String value2) {
            addCriterion("SOURCE_FILE not between", value1, value2, "sourceFile");
            return (Criteria) this;
        }

        public Criteria andSourceLineIsNull() {
            addCriterion("SOURCE_LINE is null");
            return (Criteria) this;
        }

        public Criteria andSourceLineIsNotNull() {
            addCriterion("SOURCE_LINE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceLineEqualTo(Integer value) {
            addCriterion("SOURCE_LINE =", value, "sourceLine");
            return (Criteria) this;
        }

        public Criteria andSourceLineNotEqualTo(Integer value) {
            addCriterion("SOURCE_LINE <>", value, "sourceLine");
            return (Criteria) this;
        }

        public Criteria andSourceLineGreaterThan(Integer value) {
            addCriterion("SOURCE_LINE >", value, "sourceLine");
            return (Criteria) this;
        }

        public Criteria andSourceLineGreaterThanOrEqualTo(Integer value) {
            addCriterion("SOURCE_LINE >=", value, "sourceLine");
            return (Criteria) this;
        }

        public Criteria andSourceLineLessThan(Integer value) {
            addCriterion("SOURCE_LINE <", value, "sourceLine");
            return (Criteria) this;
        }

        public Criteria andSourceLineLessThanOrEqualTo(Integer value) {
            addCriterion("SOURCE_LINE <=", value, "sourceLine");
            return (Criteria) this;
        }

        public Criteria andSourceLineIn(List<Integer> values) {
            addCriterion("SOURCE_LINE in", values, "sourceLine");
            return (Criteria) this;
        }

        public Criteria andSourceLineNotIn(List<Integer> values) {
            addCriterion("SOURCE_LINE not in", values, "sourceLine");
            return (Criteria) this;
        }

        public Criteria andSourceLineBetween(Integer value1, Integer value2) {
            addCriterion("SOURCE_LINE between", value1, value2, "sourceLine");
            return (Criteria) this;
        }

        public Criteria andSourceLineNotBetween(Integer value1, Integer value2) {
            addCriterion("SOURCE_LINE not between", value1, value2, "sourceLine");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}