//
//  IBCSend0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/09/24.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit

class IBCSend0ViewController: BaseViewController {
    
    @IBOutlet weak var btnCancel: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    @IBOutlet weak var fromChainImg: UIImageView!
    @IBOutlet weak var fromChainTxt: UILabel!
    @IBOutlet weak var toChainCard: CardView!
    @IBOutlet weak var toChainImg: UIImageView!
    @IBOutlet weak var toChainText: UILabel!
    
    @IBOutlet weak var relayerCard: CardView!
    @IBOutlet weak var relayerTxt: UILabel!
    @IBOutlet weak var relayerMsg: UILabel!

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    
    
    @IBAction func onClickCancel(_ sender: UIButton) {
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
    }
    
    @objc func onClickToChain (_ sender: UITapGestureRecognizer) {
    }
    
    @objc func onClickRelayer (_ sender: UITapGestureRecognizer) {
    }
}
