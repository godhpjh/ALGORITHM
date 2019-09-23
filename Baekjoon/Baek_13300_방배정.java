package adAlgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Baek_13300_방배정 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();		// 학생수
		int Size = sc.nextInt();	// 방 크기
		List<Student> list = new ArrayList<Student>();
		boolean check = false;
		int ans = 0;
		for(int i=0; i<N; i++) {
			check = true;
			int gender = sc.nextInt();
			int grade = sc.nextInt();
			if(i==0) list.add(new Student(gender, grade, 1));
			else {
				for(int k=0; k<list.size(); k++) {
					if(list.get(k).sex == gender && list.get(k).grade == grade) {
						list.get(k).total++;
						check = false;
						break;
					}
				}
				if(check) list.add(new Student(gender, grade, 1));
			}
		}
		
		int result = 0;
		for(Student s : list) {
			result = s.total;
			while(result > 0) {
				ans++;
				result -= Size;
			}
		}
		System.out.println(ans);
		sc.close();
	}
}

class Student {
	int sex;		// 성별
	int grade;		// 학년
	int total;		// 학년별 인원
	public Student(int sex, int grade) {
		super();
		this.sex = sex;
		this.grade = grade;
	}
	public Student(int sex, int grade, int total) {
		super();
		this.sex = sex;
		this.grade = grade;
		this.total = total;
	}
}
