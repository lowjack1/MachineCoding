package com.trello;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = "";
        Service obj = new Service();
        while (true) {
            input = in.nextLine();
            if (input.toLowerCase().equals("end")) break;
            String s[] = input.split(" ");

            if (s[0].equalsIgnoreCase("board")) {
                if (s[1].equalsIgnoreCase("create")) {
                    Board board = new Board(s[2]);
                    obj.addBoard(board);
                    System.out.println("Created board :" + board.getId());
                } else if (s[1].equalsIgnoreCase("delete")) {
                        obj.deleteBoard(s[2]);
                } else {
                    if (s[2].equalsIgnoreCase("name")) {
                        Board board = obj.getBoards().get(s[1]);
                        board.setName(s[3]);
                    } else if (s[2].equalsIgnoreCase("privacy")) {
                        Board board = obj.getBoards().get(s[1s]);
                        if (s[3].equalsIgnoreCase("private")) {
                            board.setPrivacy(PrivacyMode.PRIVATE);
                        } else if (s[3].equalsIgnoreCase("public")) {
                            board.setPrivacy(PrivacyMode.PRIVATE.PUBLIC);
                        }
                    } else if (s[2].equalsIgnoreCase("add_member")) {
                        Board board = obj.getBoards().get(s[1]);
                        User user = new User();
                        user.setName(s[3]);
                        board.addMember(user);
                    } else if (s[2].equalsIgnoreCase("remove_member")) {
                        obj.getBoards().get(s[3]).deleteMember(s[3]);
                    }
                }
            } else if (s[0].equalsIgnoreCase("list")) {
                if (s[1].equalsIgnoreCase("create")) {

                } else if (s[1].equalsIgnoreCase("delete")) {

                }
            } else if (s[0].equalsIgnoreCase("card")) {
                if (s[1].equalsIgnoreCase("create")) {

                } else if (s[1].equalsIgnoreCase("delete")) {

                }
            } else if (s[0].equalsIgnoreCase("show")) {
                obj.show();
            }
        }
    }
}
