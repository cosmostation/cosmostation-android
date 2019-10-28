//
//  WalletIovCell.swift
//  Cosmostation
//
//  Created by yongjoo on 28/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class WalletIovCell: UITableViewCell {
    
    @IBOutlet weak var totalAmount: UILabel!
    @IBOutlet weak var totalValue: UILabel!
    @IBOutlet weak var availableAmount: UILabel!
    @IBOutlet weak var depositedAmount: UILabel!
    @IBOutlet weak var rewardAmount: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    var actionDeposit: (() -> Void)? = nil
    var actionNameService: (() -> Void)? = nil

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    @IBAction func onClickDeposite(_ sender: UIButton) {
        actionDeposit?()
    }
    @IBAction func onClickNameService(_ sender: UIButton) {
        actionNameService?()
    }
}
