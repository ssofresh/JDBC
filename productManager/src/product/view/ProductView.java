package product.view;

import java.util.ArrayList;
import java.util.Scanner;

import product.model.service.ProductService;
import product.model.dto.*;

public class ProductView {
	private ProductService pservice = new ProductService();
	private Scanner sc = new Scanner(System.in);
	
	public ProductView() {}
	
	
	public void displayMenu() {
		int no;

		do {
			System.out.println("---------------------------");
			System.out.println("\n**** 프로그램 ****");
			System.out.println();
			System.out.println("1. 전체조회");
			System.out.println("2. 추가 : 추가후 id로 조회 확인함");
			System.out.println("3. 수정 : id으로 조회 후 가격 수정함");
			System.out.println("4.삭제 : id로 조회 후 삭제함");
			System.out.println("5. 검색 : 이름으로 조회함");
			System.out.println("6. 끝내기");
			System.out.println("번호 선택 : ");
			no = sc.nextInt();

			switch (no) {
			case 1: displayList(pservice.selectAll());
					break;
			case 2: 
					break;
			case 3: 
					break;
			case 4: 
					break;
			case 5: 
					break;
			case 6:
				System.out.println("\n종료(y), 취소(n) : ");
				if (sc.next().toUpperCase().charAt(0) == 'Y')
					return; // main() 으로 돌아감
				else
					break; // 메뉴 다시 반복
			default:
				System.out.println("없는 번호 입니다.");
				System.out.println("다시 입력하세요 .\n");
			}
		} while (true);
	}		
	public void displayList(ArrayList<Product> pList) {
		System.out.println(pList.size()+"개의 데이터");
		for(Product p:pList) {
			System.out.println(p);
		}
	}
	public void displayRow() {}
	public void displayRowName() {}
	public void displayInsert() {}
	public void displayUpdate() {}
	public void displayDelete() {}
}
