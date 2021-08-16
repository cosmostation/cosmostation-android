//
//  HistoryCell.swift
//  Cosmostation
//
//  Created by yongjoo on 23/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class HistoryCell: UITableViewCell {

    @IBOutlet weak var txRootCard: CardView!
    @IBOutlet weak var txTypeLabel: UILabel!
    @IBOutlet weak var txResultLabel: UILabel!
    @IBOutlet weak var txTimeLabel: UILabel!
    @IBOutlet weak var txTimeGapLabel: UILabel!
    @IBOutlet weak var txBlockLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.txRootCard.backgroundColor = COLOR_BG_GRAY
        self.txTypeLabel.textColor = .white
    }
    
    func bindHistoryLegacyView(_ history: ApiHistory.HistoryData, _ address: String) {
        txBlockLabel.text = String(history.height) + " block"
        txTypeLabel.text = WUtils.historyTitle(history.msg, address)
        txTimeLabel.text = WUtils.txTimetoString(input: history.time)
        txTimeGapLabel.text = WUtils.txTimeGap(input: history.time)
        if (history.isSuccess) { txResultLabel.isHidden = true }
        else { txResultLabel.isHidden = false }
    }
    
    func bindHistoryCustomView(_ history: ApiHistoryCustom, _ address: String) {
        txTimeLabel.text = WUtils.txTimetoString(input: history.timestamp)
        txTimeGapLabel.text = WUtils.txTimeGap(input: history.timestamp)
        txBlockLabel.text = String(history.height!) + " block"
        txTypeLabel.text = history.getMsgType(address)
        if (history.isSuccess()) { txResultLabel.isHidden = true }
        else { txResultLabel.isHidden = false }
    }
    
    func bindHistoryBnbView(_ history: BnbHistory, _ address: String) {
        txTimeLabel.text = WUtils.nodeTimetoString(input: history.timeStamp)
        txTimeGapLabel.text = WUtils.timeGap(input: history.timeStamp)
        txBlockLabel.text = String(history.blockHeight) + " block"
        txTypeLabel.text = WUtils.bnbHistoryTitle(history, address)
        txResultLabel.isHidden = true
    }
    
    func bindHistoryOkView(_ history: OkHistory.DataDetail, _ address: String) {
        txTypeLabel.text = WUtils.okHistoryTitle(history)
        txTimeLabel.text = WUtils.longTimetoString(history.timestamp! * 1000)
        txTimeGapLabel.text = WUtils.timeGap2(input: history.timestamp! * 1000)
        txBlockLabel.text = history.txhash
    }
    
}
