package com.cn.mogo.sunEdu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PushDoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PushDoExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPushContentIsNull() {
            addCriterion("push_content is null");
            return (Criteria) this;
        }

        public Criteria andPushContentIsNotNull() {
            addCriterion("push_content is not null");
            return (Criteria) this;
        }

        public Criteria andPushContentEqualTo(String value) {
            addCriterion("push_content =", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentNotEqualTo(String value) {
            addCriterion("push_content <>", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentGreaterThan(String value) {
            addCriterion("push_content >", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentGreaterThanOrEqualTo(String value) {
            addCriterion("push_content >=", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentLessThan(String value) {
            addCriterion("push_content <", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentLessThanOrEqualTo(String value) {
            addCriterion("push_content <=", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentLike(String value) {
            addCriterion("push_content like", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentNotLike(String value) {
            addCriterion("push_content not like", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentIn(List<String> values) {
            addCriterion("push_content in", values, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentNotIn(List<String> values) {
            addCriterion("push_content not in", values, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentBetween(String value1, String value2) {
            addCriterion("push_content between", value1, value2, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentNotBetween(String value1, String value2) {
            addCriterion("push_content not between", value1, value2, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNull() {
            addCriterion("push_time is null");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNotNull() {
            addCriterion("push_time is not null");
            return (Criteria) this;
        }

        public Criteria andPushTimeEqualTo(Date value) {
            addCriterion("push_time =", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotEqualTo(Date value) {
            addCriterion("push_time <>", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThan(Date value) {
            addCriterion("push_time >", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("push_time >=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThan(Date value) {
            addCriterion("push_time <", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThanOrEqualTo(Date value) {
            addCriterion("push_time <=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeIn(List<Date> values) {
            addCriterion("push_time in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotIn(List<Date> values) {
            addCriterion("push_time not in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeBetween(Date value1, Date value2) {
            addCriterion("push_time between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotBetween(Date value1, Date value2) {
            addCriterion("push_time not between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushDeviceIsNull() {
            addCriterion("push_device is null");
            return (Criteria) this;
        }

        public Criteria andPushDeviceIsNotNull() {
            addCriterion("push_device is not null");
            return (Criteria) this;
        }

        public Criteria andPushDeviceEqualTo(Integer value) {
            addCriterion("push_device =", value, "pushDevice");
            return (Criteria) this;
        }

        public Criteria andPushDeviceNotEqualTo(Integer value) {
            addCriterion("push_device <>", value, "pushDevice");
            return (Criteria) this;
        }

        public Criteria andPushDeviceGreaterThan(Integer value) {
            addCriterion("push_device >", value, "pushDevice");
            return (Criteria) this;
        }

        public Criteria andPushDeviceGreaterThanOrEqualTo(Integer value) {
            addCriterion("push_device >=", value, "pushDevice");
            return (Criteria) this;
        }

        public Criteria andPushDeviceLessThan(Integer value) {
            addCriterion("push_device <", value, "pushDevice");
            return (Criteria) this;
        }

        public Criteria andPushDeviceLessThanOrEqualTo(Integer value) {
            addCriterion("push_device <=", value, "pushDevice");
            return (Criteria) this;
        }

        public Criteria andPushDeviceIn(List<Integer> values) {
            addCriterion("push_device in", values, "pushDevice");
            return (Criteria) this;
        }

        public Criteria andPushDeviceNotIn(List<Integer> values) {
            addCriterion("push_device not in", values, "pushDevice");
            return (Criteria) this;
        }

        public Criteria andPushDeviceBetween(Integer value1, Integer value2) {
            addCriterion("push_device between", value1, value2, "pushDevice");
            return (Criteria) this;
        }

        public Criteria andPushDeviceNotBetween(Integer value1, Integer value2) {
            addCriterion("push_device not between", value1, value2, "pushDevice");
            return (Criteria) this;
        }

        public Criteria andPushStatusIsNull() {
            addCriterion("push_status is null");
            return (Criteria) this;
        }

        public Criteria andPushStatusIsNotNull() {
            addCriterion("push_status is not null");
            return (Criteria) this;
        }

        public Criteria andPushStatusEqualTo(Integer value) {
            addCriterion("push_status =", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusNotEqualTo(Integer value) {
            addCriterion("push_status <>", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusGreaterThan(Integer value) {
            addCriterion("push_status >", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("push_status >=", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusLessThan(Integer value) {
            addCriterion("push_status <", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusLessThanOrEqualTo(Integer value) {
            addCriterion("push_status <=", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusIn(List<Integer> values) {
            addCriterion("push_status in", values, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusNotIn(List<Integer> values) {
            addCriterion("push_status not in", values, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusBetween(Integer value1, Integer value2) {
            addCriterion("push_status between", value1, value2, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("push_status not between", value1, value2, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andSenderAccountTypeIsNull() {
            addCriterion("sender_account_type is null");
            return (Criteria) this;
        }

        public Criteria andSenderAccountTypeIsNotNull() {
            addCriterion("sender_account_type is not null");
            return (Criteria) this;
        }

        public Criteria andSenderAccountTypeEqualTo(Integer value) {
            addCriterion("sender_account_type =", value, "senderAccountType");
            return (Criteria) this;
        }

        public Criteria andSenderAccountTypeNotEqualTo(Integer value) {
            addCriterion("sender_account_type <>", value, "senderAccountType");
            return (Criteria) this;
        }

        public Criteria andSenderAccountTypeGreaterThan(Integer value) {
            addCriterion("sender_account_type >", value, "senderAccountType");
            return (Criteria) this;
        }

        public Criteria andSenderAccountTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sender_account_type >=", value, "senderAccountType");
            return (Criteria) this;
        }

        public Criteria andSenderAccountTypeLessThan(Integer value) {
            addCriterion("sender_account_type <", value, "senderAccountType");
            return (Criteria) this;
        }

        public Criteria andSenderAccountTypeLessThanOrEqualTo(Integer value) {
            addCriterion("sender_account_type <=", value, "senderAccountType");
            return (Criteria) this;
        }

        public Criteria andSenderAccountTypeIn(List<Integer> values) {
            addCriterion("sender_account_type in", values, "senderAccountType");
            return (Criteria) this;
        }

        public Criteria andSenderAccountTypeNotIn(List<Integer> values) {
            addCriterion("sender_account_type not in", values, "senderAccountType");
            return (Criteria) this;
        }

        public Criteria andSenderAccountTypeBetween(Integer value1, Integer value2) {
            addCriterion("sender_account_type between", value1, value2, "senderAccountType");
            return (Criteria) this;
        }

        public Criteria andSenderAccountTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sender_account_type not between", value1, value2, "senderAccountType");
            return (Criteria) this;
        }

        public Criteria andPushPeopleIsNull() {
            addCriterion("push_people is null");
            return (Criteria) this;
        }

        public Criteria andPushPeopleIsNotNull() {
            addCriterion("push_people is not null");
            return (Criteria) this;
        }

        public Criteria andPushPeopleEqualTo(Integer value) {
            addCriterion("push_people =", value, "pushPeople");
            return (Criteria) this;
        }

        public Criteria andPushPeopleNotEqualTo(Integer value) {
            addCriterion("push_people <>", value, "pushPeople");
            return (Criteria) this;
        }

        public Criteria andPushPeopleGreaterThan(Integer value) {
            addCriterion("push_people >", value, "pushPeople");
            return (Criteria) this;
        }

        public Criteria andPushPeopleGreaterThanOrEqualTo(Integer value) {
            addCriterion("push_people >=", value, "pushPeople");
            return (Criteria) this;
        }

        public Criteria andPushPeopleLessThan(Integer value) {
            addCriterion("push_people <", value, "pushPeople");
            return (Criteria) this;
        }

        public Criteria andPushPeopleLessThanOrEqualTo(Integer value) {
            addCriterion("push_people <=", value, "pushPeople");
            return (Criteria) this;
        }

        public Criteria andPushPeopleIn(List<Integer> values) {
            addCriterion("push_people in", values, "pushPeople");
            return (Criteria) this;
        }

        public Criteria andPushPeopleNotIn(List<Integer> values) {
            addCriterion("push_people not in", values, "pushPeople");
            return (Criteria) this;
        }

        public Criteria andPushPeopleBetween(Integer value1, Integer value2) {
            addCriterion("push_people between", value1, value2, "pushPeople");
            return (Criteria) this;
        }

        public Criteria andPushPeopleNotBetween(Integer value1, Integer value2) {
            addCriterion("push_people not between", value1, value2, "pushPeople");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(Integer value) {
            addCriterion("receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(Integer value) {
            addCriterion("receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(Integer value) {
            addCriterion("receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(Integer value) {
            addCriterion("receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(Integer value) {
            addCriterion("receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(Integer value) {
            addCriterion("receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<Integer> values) {
            addCriterion("receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<Integer> values) {
            addCriterion("receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(Integer value1, Integer value2) {
            addCriterion("receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(Integer value1, Integer value2) {
            addCriterion("receiver not between", value1, value2, "receiver");
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