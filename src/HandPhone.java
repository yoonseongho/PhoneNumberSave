import java.io.*;
import java.util.Scanner;

public class HandPhone {
    static String fname = "/Users/yunseongho/Documents/IdeaProjects/HandPhone/temp";

    static class address {
        String name;
        String age;
        String phone;

        address(String s1, String s2, String s3) {
            this.name = s1;
            this.age = s2;
            this.phone = s3;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String select = "";
        System.out.println("=========== 연락처 관리 ===========");

        while(select != "4") {
            print_menu();
            select = sc.next();

            switch (select) {
                case "1":
                    view_juso();
                    break;
                case "2":
                    add_juso();
                    break;
                case "3":
                    delete_juso();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("잘못 입력했습니다. 다시 선택하세요");
            }
        }
    }

    /* 처음에 사용자의 선택을 위한 메뉴 출력 */
    static void print_menu() {
        System.out.println();
        System.out.println("1. 연락처 출력");
        System.out.println("2. 연락처 등록");
        System.out.println("3. 연락처 삭제");
        System.out.println("4. 끝내기");
        System.out.print("입력 >> ");
    }

    /* 연락처 파일에서 기존 입력된 내용을 읽어서 출력 */
    static void view_juso() throws IOException {
        String str = "";

        /* 처음에 fname 파일이 없으면 빈 파일 생성 */
        File f = new File(fname);
        if(!f.exists()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fname));
            bw.close();
        }

        BufferedReader br = new BufferedReader(new FileReader(fname));
        int i;

        for(i = 1; ; i++) { /* i는 계속 1씩 증가하는 무한 루프 */
            if (!br.ready()) /* 파일을 읽을 수 없으면 */
                break;
            else {
                str = br.readLine();
                System.out.println(i + ": " + str);
            }
        }

        /* i가 1이면 실제 파일에는 내용이 없음 */
        if(i == 1)
            System.out.println("=========== 연락처 파일에 전화번호가 하나도 없어요. ===========");

            br.close();
        }

        /* 연락처 추가 */
        static void add_juso() throws IOException {
            Scanner sc = new Scanner(System.in);
            address adr = new address("", "", "");

            String wstr = "";

            /* 파일을 추가 모드로 열기 */
            BufferedWriter bw = new BufferedWriter(new FileWriter(fname, true));

            System.out.print("이름 입력 >> ");
            adr.name = sc.nextLine();
            System.out.print("나이 입력 >> ");
            adr.age = sc.nextLine();
            System.out.print("전화번호 입력 >> ");
            adr.phone = sc.nextLine();

            /* 입력된 3개의 값을 하나의 문자열로 만듦 */
            wstr = adr.name + "\t" + adr.age + "\t" + adr.phone;

            bw.write(wstr); /* 파일에 문자열 쓰기 */
            bw.newLine();

            bw.close();
        }

        /* 연락처 파일에 선택한 연락처를 제거 */
        static void delete_juso() throws IOException {
            Scanner sc = new Scanner(System.in);

            /* 연락처 파일의 내용 전체를 저장하기 위한 문자열 배열 */
            String[] read_str = new String[100]; /* 최대 연락처 개수를 100개로 지정 */
            String str = "";
            int del_line, i, count = 0;

            BufferedReader br = new BufferedReader(new FileReader(fname));

            /* 연락처 파일이 없으면 돌아간다 */
            if(!br.ready()) {
                System.out.println("=========== 연락처 파일이 없습니다. ===========");
                return;
            }

            System.out.print("삭제할 행 번호는 >> ");
            del_line = sc.nextInt();

            for(i = 0; i < 100; i++) { /* 파일에 있는 동안에 수행, 단 최대 100개 */
                if((str = br.readLine()) == null)
                    break;

                if(i + 1 != del_line) { /* 삭제하는 행이 아니라면 추가 */
                    read_str[count] = str;
                    count++;
                }
                else
                    System.out.println(del_line + "행이 삭제 되었습니다.");
                }

                br.close();

                /* 파일을 쓰기 모드로 열고 새로운 내용을 쓴다. */
                BufferedWriter bw = new BufferedWriter(new FileWriter(fname));

                for(i = 0; i < count; i++) {
                    bw.write(read_str[i]);
                    bw.newLine();
                }
                bw.close();
            }
        }