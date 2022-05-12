package cliApp.lotto;

import java.util.Random;
import java.util.Scanner;

public class Main {
    //전역변수 초기화
    static final int MAX_ARRAY=100;
    static int[][] arrayLotto=new int[MAX_ARRAY][6];
    static int idxCurrent =-1;
    public static void main(String[] args) {
        //지역변수 초기화
        Scanner sc1=new Scanner(System.in);
        int menu= -1;
        //menu가 0이 나오기 전까지 반복
        while(menu !=0){
            //메뉴 출력
            printMenu();
            //입력
            menu =sc1.nextInt();
            switch (menu){
                //menu가 0이면 반복문이 끝남
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                case 1:
                    //함수 호출(중복상관없는 로또번호)
                    makeLotto();
                    System.out.println("중복되는 로또 번호 생성합니다.");
                    break;
                case 2:
                    //함수 호출(중복x 로또번호)
                    makeLotto2();
                    System.out.println("중복되지않는 로또 번호를 생성합니다.");
                    break;
                    //함수 호출(지금까지 저장된 로또 번호 출력)
                case 3:
                    System.out.println("생성된 로또번호를 봅니다.");
                    showLottoList();
                    break;
                default:
                    break;
            }
        }
    }

    private static void makeLotto2() {
        //지역변수 초기화
        Random rnd=new Random();
        int[] lotto = new int[6];
        //로또번호6개를 뽑아야 되기때문에 6번 반복
        for(int i=0;i<6;i++){
            boolean unique=true;
            //로또번호를 1~45중에서 1개 뽑음
            int myNumber=rnd.nextInt(45)+1;
            //전 로또번호와 중복 될 수 있기때문에 i보다 작은 값들을 lotto의 인덱스 값에 넣고 myname과 같은지 비교
            for(int j=0;j<i;j++){
                if (myNumber==lotto[j]) {
                    //중복되면 i를 -1해서 다시 뽑을 수 있게함
                    i-=1;
                    //unique를 false로해서 번호가 추가되지않게 만듬
                    unique=false;
                }
            }
            if(unique)
                //번호추가
                lotto[i]=myNumber;
        }
        //6개의 번호 저장을 위해 함수 호출
        saveLotto(lotto);
    }

    private static void makeLotto() {
        Random rnd=new Random();
        int[] lotto = new int[6];
        for(int i=0;i<6;i++){
            int myNumber=rnd.nextInt(45)+1;
            lotto[i]=myNumber;
        }
        saveLotto(lotto);
    }

    private static void saveLotto(int[] lotto) {
        idxCurrent++;
        //idxCurrent가 100이 되면 함수를 리턴시킴
        if(idxCurrent ==MAX_ARRAY){
            System.out.println("저장범위가 초과했습니다.");
            return;
        }
        for(int i=0;i<6;i++){
            //arrayLotto[idxCurrent][i]에 인자로 받은 lotto[i]를 넣는다
            arrayLotto[idxCurrent][i]=lotto[i];
            //출력문
            System.out.println((i+1)+":"+arrayLotto[idxCurrent][i]);
        }
    }

    private static void printMenu(){
        System.out.println("+-------------------------+");
        System.out.println("    1.로또 번호 생성(중복O)");
        System.out.println("    2.로또 번호 생성(중복x");
        System.out.println("    3.생성된 로또 번호 보기");
        System.out.println("    0.종료");
        System.out.println("+-------------------------+");
        System.out.print("  메뉴를 선택하세요.");
    }
    private static void showLottoList(){
        //출력
        for(int i=0;i<=idxCurrent;i++){
            System.out.print((i+1)+"번째 로또번호 :");
            for(int j=0;j<6;j++){
                System.out.print(arrayLotto[i][j]+"\t");
            }
            System.out.println("\n");
        }
    }
}
