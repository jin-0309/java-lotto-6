package lotto.controller;

import lotto.model.DrawResult;
import lotto.model.Lotto;
import lotto.model.LottoGame;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {

    private final InputView inputView;

    private final OutputView outputView;

    public LottoGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printRequestAmount();
        LottoGame lottoGame = getNewLottoGame();
        outputView.printIssuanceLotto(lottoGame.getUser().issuanceLotto());
        outputView.printRequestWinNumbers();
        Lotto lotto = new Lotto(inputView.inputWinNumbers());
        outputView.printRequestBonusNumber();
        DrawResult drawResult = new DrawResult(lotto, inputView.inputInteger());
        drawResult.validateDuplication();
        lottoGame.calculationResult(drawResult);
        outputView.printResult(lottoGame);
    }

    public LottoGame getNewLottoGame() {
        LottoGame lottoGame;
        while (true) {
            try {
                lottoGame = new LottoGame(inputView.inputInteger());
                break;
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return lottoGame;
    }

    public DrawResult getNewWinnLotto() {
        Lotto lotto;
        while (true) {
            try {
                lotto = new Lotto(inputView.inputWinNumbers());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        outputView.printRequestBonusNumber();
        return lotto;
    }
}
