package com.example.minesweeper;

import com.example.minesweeper.data.Board;
import com.example.minesweeper.data.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("")
    public String getIndexPage(Model model)

        {
            if(MinesweeperApplication.game == null) {
                MinesweeperApplication.game = new Game(5);

            }
            model.addAttribute("game", getGameHtml(MinesweeperApplication.game));

            return "index";
        }

        @GetMapping("/check")
        public String checkField (Model model, @RequestParam int row, @RequestParam int column){


            if(MinesweeperApplication.game == null) {
                MinesweeperApplication.game = new Game(5);

            }

            MinesweeperApplication.game.checkField(row, column);

            model.addAttribute("game", getGameHtml(MinesweeperApplication.game));

            return "index";
        }

        private String getGameHtml (Game game){
            var html = new StringBuilder();

            for (int i = 0; i < game.getSize(); i++) {
                html.append("<div class=\"row\">");
                for (int j = 0; j < game.getSize(); j++) {
                    html.append("<div class=\"field\">" + getFieldIcon(game.getFieldValue(i, j), i, j) + "</div>");
                }
                html.append("</div>");

            }

            return html.toString();

        }

        private String getFieldIcon ( int i, int row, int column){

            switch (i) {
                case Board.MINE_VALUE: {
                    return "<div class=\"field-bomb\"><img src=\"/img/bomb.svg\" class=\"field-icon\"></div>";
                }
                case Board.FIELD_FLAGGED: {
                    return "<img src=\"/img/flag.svg\" class=\"field-icon\">";
                }
                case Board.FIELD_IS_EMPTY: {
                    return "<div class=\"field-empty\">&nbsp;</div>";
                }
                case Board.FIELD_CLOSED: {
                    return "<a class=\"field-closed\" href=\"/check?row="+row+"&column="+column+"\">&nbsp;</a>";

                }
            }
            return "<div class=\"field-number\"><span class=\"number\">"+i+"</span></div>";
        }
    }
