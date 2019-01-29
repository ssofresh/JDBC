package book.mvc.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import book.mvc.controller.BookController;
import book.mvc.model.vo.Book;

public class BookMenu {
	private Scanner sc = new Scanner(System.in);
	private BookController bcontroll = new BookController();
	
	public BookMenu() {}
	
	public void displayMenu() {
		int no;
		do {
			System.out.println("\n******************************************************");
			System.out.println();
			System.out.println("1. 도서 추가");
			System.out.println("2. 도서 가격 수정");
			System.out.println("3. 도서 삭제");
			System.out.println("4. 도서 아이디로 조회");
			System.out.println("5. 도서 제목으로 조회 : 리스트사용");
			System.out.println("6. 도서 제목으로 조회 : 맵사용");
			System.out.println("7. 도서 목록 전체 조회 : 리스트 사용");
			System.out.println("8. 도서 목록 전체 조회 : 맵 사용");
			System.out.println("9. 끝내기");
			System.out.println("번호 선택 : ");
			no = sc.nextInt();
			
			switch(no) {
			case 1: bcontroll.insertBook(inputBook());
					break;
			case 2: bcontroll.updateBook(modifyBook());
					break;
			case 3: bcontroll.deleteBook(inputBookId());
					break;
			case 4: bcontroll.searchBook(inputBookId());
					break;
			case 5: bcontroll.searchBookList(inputBookTitle());
					break;
			case 6: bcontroll.searchBookMap(inputBookTitle());
					break;
			case 7: bcontroll.selectAllList(); break;
			case 8: bcontroll.selectAllMap(); break;
			case 9: System.out.println("정말로 종료하시겠습니까?(Y/N) : ");
			 		if(sc.next().toUpperCase().charAt(0) == 'Y')
			 			return;
			default: System.out.println("\n번호 잘못 선택");
					  System.out.println("다시 입력하세요");
			
			}
		} while(true);
	}

	private String inputBookTitle() {
		System.out.println("검색할 도서제목 : ");
		sc.nextLine();	//입력버퍼에 남은 엔터키 제거
		return sc.nextLine();
	}

	private int inputBookId() {
		System.out.println("검색할 도서번호 : ");
		return sc.nextInt();
	}

	private Book modifyBook() {
		Book b = new Book();
		System.out.println("수정할 대상 도서 번호 : ");
		b.setBookId(sc.nextInt());
		System.out.println("변경할 가격 : ");
		b.setPrice(sc.nextInt());
		return b;
	}

	private Book inputBook() {
		Book b = new Book();
		System.out.println("\n 새로 등록할 도서정보를 입력하세요");
		System.out.println("도서 제목 : ");
		sc.nextLine();
		b.setTitle(sc.nextLine());
		System.out.println("저자이름 : ");
		b.setAuthor(sc.next());
		System.out.println("출판사 : ");
		b.setPublisher(sc.next());
		System.out.println("출판날짜[yyyy-MM-dd] : ");
		b.setPublishDate(java.sql.Date.valueOf(sc.next()));
		System.out.println("가격 : ");
		b.setPrice(sc.nextInt());
		return b;
	}

	public void printError(String message) {
		// Controller 에서 넘어온 에러메세지 출력용 
		System.out.println("\n프로그램 오류 발생!");
		System.out.println("시스템 관리자에게 문의하십시오.");
		System.out.println("오류 메세지 : "+message);
		
	}

	public void printBook(Book b) {
		System.out.println("\n조회된 도서 정보는 다음과 같습니다");
		System.out.println(b);
	}
	
	public void printList(ArrayList<Book> bList) {
		System.out.println("\n조회된 도서의수 : "+bList.size());
		for(Book b:bList) {
			System.out.println(b);
		}
	}
	
	public void printMap(HashMap<Integer, Book> bMap) {
		Iterator<Integer> keyIter = bMap.keySet().iterator();
		while(keyIter.hasNext()) {
			System.out.println(bMap.get(keyIter.next()));
		}
	}

}
