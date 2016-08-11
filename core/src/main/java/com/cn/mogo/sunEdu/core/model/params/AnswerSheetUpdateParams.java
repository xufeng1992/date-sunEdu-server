package com.cn.mogo.sunEdu.core.model.params;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by FE on 2016/6/25.
 */
public class AnswerSheetUpdateParams extends BasicParams{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5094068737561539214L;
	private Integer id ;
    private Integer subjectId ;
    private Integer workId ;
    private String teacherComment ;
    private String teacherVoice;
    private Double score ;
    private MultipartFile voiceFile ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }

    public String getTeacherVoice() {
        return teacherVoice;
    }

    public void setTeacherVoice(String teacherVoice) {
        this.teacherVoice = teacherVoice;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public MultipartFile getVoiceFile() {
        return voiceFile;
    }

    public void setVoiceFile(MultipartFile voiceFile) {
        this.voiceFile = voiceFile;
    }
}
