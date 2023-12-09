package master.resume.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 投递快照表
 * </p>
 *
 * @author huadexuan
 * @since 2023-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("resume_info")
public class ResumeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 简历id
     */
    private Long resumeId;

    /**
     * 人才编号
     */
    private String talentNo;

    /**
     * 机构审核状态
     */
    private Integer orgAuditStatus;

    /**
     * 批次id
     */
    private Long orgBatchId;

    /**
     * 联系方式开关 1-开 0-关
     */
    private Integer contactSwitch;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别 1-男 2-女
     */
    private Integer gender;

    /**
     * 出生年月 1999-10
     */
    private Date birthDate;

    /**
     * 健康状态 1-健康或良好 2-一般或较弱 3-有慢性病 4-残疾
     */
    private Integer health;

    /**
     * 学历 学历基础数据
     */
    private Integer degree;

    /**
     * 最高学历所在地id
     */
    private Long degreeLocationId;

    /**
     * 最高学历所在地
     */
    private String degreeLocation;

    /**
     * 职位id
     */
    private Long positionId;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 国家id
     */
    private Long countryId;

    /**
     * 国家名称
     */
    private String countryName;

    /**
     * 城市id
     */
    private Long cityId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 当前位置id
     */
    private Integer currentLocationId;

    /**
     * 当前位置
     */
    private String currentLocation;

    /**
     * 机构/学校id
     */
    private Long orgId;

    /**
     * 机构/学校名称
     */
    private String orgName;

    /**
     * 院系所名称
     */
    private String departmentName;

    /**
     * 微信号
     */
    private String wechatNo;

    /**
     * 证件类型 1-内地身份证号 2-港澳居民居住证
     */
    private Integer certificateType;

    /**
     * 证件号码
     */
    private String certificateNo;

    /**
     * 政治面貌 1-群众 2-中共党员
     */
    private Integer politicalStatus;

    /**
     * 民族类型
     */
    private Integer nationType;

    /**
     * 民族
     */
    private String nationName;

    /**
     * 婚姻状态 已婚、未婚、已结婚、未结婚、保密
     */
    private Integer maritalStatus;

    /**
     * 生育情况 1-未生育 2-已生育一孩 3-已生育二孩 4-已生育二孩以上
     */
    private Integer fertilityStatus;

    /**
     * 宗教信仰
     */
    private Integer religion;

    /**
     * 个人简介
     */
    private String description;

    /**
     * 简历本科学校
     */
    private String undergraduateSchool;

    /**
     * 本科学校的标签id(211/985那些)
     */
    private String undergraduateSchoolTags;

    /**
     * 简历硕士学校
     */
    private String masterSchool;

    /**
     * 硕士学校的标签(如果存在多个硕士经历,则保存标签的并集)
     */
    private String masterSchoolTags;

    /**
     * 简历博士学校
     */
    private String doctorSchool;

    /**
     * 博士学校的标签
     */
    private String doctorSchoolTags;

    /**
     * 职称
     */
    private String professionalTitle;

    /**
     * 研究方向
     */
    private String researchDirection;

    /**
     * 学科领域
     */
    private String subjectArea;

    /**
     * 一级学科领域
     */
    private String firstLevelSubjectArea;

    /**
     * 个人网站地址
     */
    private String personalWebsite;

    /**
     * 论文
     */
    private String paper;

    /**
     * 教育经历
     */
    private String educationExperience;

    /**
     * 博士后经历
     */
    private String postdocExperience;

    /**
     * 工作经历
     */
    private String workExperience;

    /**
     * 教学经历
     */
    private String teachingExperience;

    /**
     * 项目/课题
     */
    private String projectExperience;

    /**
     * 专利/成果
     */
    private String patentExperience;

    /**
     * 奖项/称号
     */
    private String awardsExperience;

    /**
     * 其他说明
     */
    private String otherExperience;

    /**
     * 其他排名，例如QS世界大学，泰晤士高等教育世界大学，U.S.News世界大学，ARWU软科世界大学
     */
    private String otherRanking;

    /**
     * 意向地区
     */
    private String area;

    /**
     * 求职意向
     */
    private String positionIntention;

    /**
     * 简历完成度 百分比
     */
    private Integer percent;

    /**
     * 是否填写教育经历 本科以及以上填写完整才算
     */
    private Integer hasEducation;

    /**
     * 是否填写博士后经历
     */
    private Integer hasPostdoc;

    /**
     * 是否填写工作经历
     */
    private Integer hasWork;

    /**
     * 是否填写教学经历
     */
    private Integer hasTeaching;

    /**
     * 是否填写论文/著作
     */
    private Integer hasPapers;

    /**
     * 是否填写项目/课题
     */
    private Integer hasProject;

    /**
     * 是否填写专利/成果
     */
    private Integer hasPatent;

    /**
     * 是否填写奖项/称号
     */
    private Integer hasAwardTitle;

    /**
     * 是否填写其他说明
     */
    private Integer hasDescription;

    /**
     * 是否上传简历附件
     */
    private Integer hasAttachment;

    /**
     * 是否第一次上传简历附件
     */
    private Integer hasFirstAttachment;

    /**
     * 是否有海外经历 教育经历、工作经历、联合培养有海外经历才算
     */
    private Integer hasOverseasExperience;

    /**
     * 是否有海外教育经历
     */
    private Integer hasOverseasEducation;

    /**
     * 是否有海外工作经历
     */
    private Integer hasOverseasWork;

    /**
     * 是否有联合培养经历
     */
    private Integer hasOverseasJoint;

    /**
     * 是否985
     */
    private Integer hasProject985;

    /**
     * 是否211
     */
    private Integer hasProject211;

    /**
     * 是否双一流
     */
    private Integer hasDoubleFirst;

    /**
     * 是否主动报名 0不是 1是
     */
    private Integer hasVoluntary;

    /**
     * 附件简历地址
     */
    private String attachment;

    /**
     * 附件简历ID
     */
    private Long attachmentId;

    /**
     * 创建者id
     */
    private Long createUserId;

    /**
     * 更新者id
     */
    private Long updateUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 逻辑删除 0不删除 1已删除
     */
    private Integer delFlag;


}
