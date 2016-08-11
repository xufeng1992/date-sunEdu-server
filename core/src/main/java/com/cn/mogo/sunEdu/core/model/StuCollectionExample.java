package com.cn.mogo.sunEdu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StuCollectionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StuCollectionExample() {
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

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(Integer value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(Integer value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(Integer value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(Integer value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<Integer> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<Integer> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andCollectionTypesIsNull() {
            addCriterion("collection_types is null");
            return (Criteria) this;
        }

        public Criteria andCollectionTypesIsNotNull() {
            addCriterion("collection_types is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionTypesEqualTo(Integer value) {
            addCriterion("collection_types =", value, "collectionTypes");
            return (Criteria) this;
        }

        public Criteria andCollectionTypesNotEqualTo(Integer value) {
            addCriterion("collection_types <>", value, "collectionTypes");
            return (Criteria) this;
        }

        public Criteria andCollectionTypesGreaterThan(Integer value) {
            addCriterion("collection_types >", value, "collectionTypes");
            return (Criteria) this;
        }

        public Criteria andCollectionTypesGreaterThanOrEqualTo(Integer value) {
            addCriterion("collection_types >=", value, "collectionTypes");
            return (Criteria) this;
        }

        public Criteria andCollectionTypesLessThan(Integer value) {
            addCriterion("collection_types <", value, "collectionTypes");
            return (Criteria) this;
        }

        public Criteria andCollectionTypesLessThanOrEqualTo(Integer value) {
            addCriterion("collection_types <=", value, "collectionTypes");
            return (Criteria) this;
        }

        public Criteria andCollectionTypesIn(List<Integer> values) {
            addCriterion("collection_types in", values, "collectionTypes");
            return (Criteria) this;
        }

        public Criteria andCollectionTypesNotIn(List<Integer> values) {
            addCriterion("collection_types not in", values, "collectionTypes");
            return (Criteria) this;
        }

        public Criteria andCollectionTypesBetween(Integer value1, Integer value2) {
            addCriterion("collection_types between", value1, value2, "collectionTypes");
            return (Criteria) this;
        }

        public Criteria andCollectionTypesNotBetween(Integer value1, Integer value2) {
            addCriterion("collection_types not between", value1, value2, "collectionTypes");
            return (Criteria) this;
        }

        public Criteria andCollectionIdIsNull() {
            addCriterion("collection_id is null");
            return (Criteria) this;
        }

        public Criteria andCollectionIdIsNotNull() {
            addCriterion("collection_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionIdEqualTo(Integer value) {
            addCriterion("collection_id =", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdNotEqualTo(Integer value) {
            addCriterion("collection_id <>", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdGreaterThan(Integer value) {
            addCriterion("collection_id >", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("collection_id >=", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdLessThan(Integer value) {
            addCriterion("collection_id <", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdLessThanOrEqualTo(Integer value) {
            addCriterion("collection_id <=", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdIn(List<Integer> values) {
            addCriterion("collection_id in", values, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdNotIn(List<Integer> values) {
            addCriterion("collection_id not in", values, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdBetween(Integer value1, Integer value2) {
            addCriterion("collection_id between", value1, value2, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("collection_id not between", value1, value2, "collectionId");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsIsNull() {
            addCriterion("sub_collection_ids is null");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsIsNotNull() {
            addCriterion("sub_collection_ids is not null");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsEqualTo(String value) {
            addCriterion("sub_collection_ids =", value, "subCollectionIds");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsNotEqualTo(String value) {
            addCriterion("sub_collection_ids <>", value, "subCollectionIds");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsGreaterThan(String value) {
            addCriterion("sub_collection_ids >", value, "subCollectionIds");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsGreaterThanOrEqualTo(String value) {
            addCriterion("sub_collection_ids >=", value, "subCollectionIds");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsLessThan(String value) {
            addCriterion("sub_collection_ids <", value, "subCollectionIds");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsLessThanOrEqualTo(String value) {
            addCriterion("sub_collection_ids <=", value, "subCollectionIds");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsLike(String value) {
            addCriterion("sub_collection_ids like", value, "subCollectionIds");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsNotLike(String value) {
            addCriterion("sub_collection_ids not like", value, "subCollectionIds");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsIn(List<String> values) {
            addCriterion("sub_collection_ids in", values, "subCollectionIds");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsNotIn(List<String> values) {
            addCriterion("sub_collection_ids not in", values, "subCollectionIds");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsBetween(String value1, String value2) {
            addCriterion("sub_collection_ids between", value1, value2, "subCollectionIds");
            return (Criteria) this;
        }

        public Criteria andSubCollectionIdsNotBetween(String value1, String value2) {
            addCriterion("sub_collection_ids not between", value1, value2, "subCollectionIds");
            return (Criteria) this;
        }

        public Criteria andCollectionTimeIsNull() {
            addCriterion("collection_time is null");
            return (Criteria) this;
        }

        public Criteria andCollectionTimeIsNotNull() {
            addCriterion("collection_time is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionTimeEqualTo(Date value) {
            addCriterion("collection_time =", value, "collectionTime");
            return (Criteria) this;
        }

        public Criteria andCollectionTimeNotEqualTo(Date value) {
            addCriterion("collection_time <>", value, "collectionTime");
            return (Criteria) this;
        }

        public Criteria andCollectionTimeGreaterThan(Date value) {
            addCriterion("collection_time >", value, "collectionTime");
            return (Criteria) this;
        }

        public Criteria andCollectionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("collection_time >=", value, "collectionTime");
            return (Criteria) this;
        }

        public Criteria andCollectionTimeLessThan(Date value) {
            addCriterion("collection_time <", value, "collectionTime");
            return (Criteria) this;
        }

        public Criteria andCollectionTimeLessThanOrEqualTo(Date value) {
            addCriterion("collection_time <=", value, "collectionTime");
            return (Criteria) this;
        }

        public Criteria andCollectionTimeIn(List<Date> values) {
            addCriterion("collection_time in", values, "collectionTime");
            return (Criteria) this;
        }

        public Criteria andCollectionTimeNotIn(List<Date> values) {
            addCriterion("collection_time not in", values, "collectionTime");
            return (Criteria) this;
        }

        public Criteria andCollectionTimeBetween(Date value1, Date value2) {
            addCriterion("collection_time between", value1, value2, "collectionTime");
            return (Criteria) this;
        }

        public Criteria andCollectionTimeNotBetween(Date value1, Date value2) {
            addCriterion("collection_time not between", value1, value2, "collectionTime");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusIsNull() {
            addCriterion("collection_status is null");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusIsNotNull() {
            addCriterion("collection_status is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusEqualTo(Integer value) {
            addCriterion("collection_status =", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusNotEqualTo(Integer value) {
            addCriterion("collection_status <>", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusGreaterThan(Integer value) {
            addCriterion("collection_status >", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("collection_status >=", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusLessThan(Integer value) {
            addCriterion("collection_status <", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusLessThanOrEqualTo(Integer value) {
            addCriterion("collection_status <=", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusIn(List<Integer> values) {
            addCriterion("collection_status in", values, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusNotIn(List<Integer> values) {
            addCriterion("collection_status not in", values, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusBetween(Integer value1, Integer value2) {
            addCriterion("collection_status between", value1, value2, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("collection_status not between", value1, value2, "collectionStatus");
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