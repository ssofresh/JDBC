package employee.model.vo;

import java.sql.Date;

//vo(value object : 값 저장용 객체)
//== do(domain object) == dto(data transfer object)
//== entity == bean
//데이터베이스 테이블의 한 행의 정보를 저장할 객체를 말함

//1. 필드는 모두 private 이어야 함
//2. 기본생성자와 매개변수 있는 생성자가 있어야 함
//3. 모든 필드에 대한 setter 와 getter 가 있어야함
//4. 직렬화 처리해야 함
public class Employee implements java.io.Serializable{
	private static final long serialVersionUID = 7L;

	private String empId;
	private String empName;
	private String empNo;
	private String eMail;
	private String phone;
	private Date hireDate;
	private String jobID;
	private int salary;
	private double bonusPct;
	private String marriage;
	private String mgrId;
	private String deptId;
	
	public Employee() {}

	public Employee(String empId, String empName, String empNo, String eMail, String phone, Date hireDate, String jobID,
			int salary, double bonusPct, String marriage, String mgrId, String deptId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.eMail = eMail;
		this.phone = phone;
		this.hireDate = hireDate;
		this.jobID = jobID;
		this.salary = salary;
		this.bonusPct = bonusPct;
		this.marriage = marriage;
		this.mgrId = mgrId;
		this.deptId = deptId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public double getBonusPct() {
		return bonusPct;
	}

	public void setBonusPct(double bonusPct) {
		this.bonusPct = bonusPct;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getMgrId() {
		return mgrId;
	}

	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return this.empId + ", " + this.empName + ", " + this.empNo + ", " + this.eMail +", " +
				this.phone + ", " + this.hireDate + ", " + this.salary +", " + this.bonusPct +
				", " + this.jobID + ", " + this.marriage + ", " + this.mgrId + ", " + this.deptId;
	}
}
