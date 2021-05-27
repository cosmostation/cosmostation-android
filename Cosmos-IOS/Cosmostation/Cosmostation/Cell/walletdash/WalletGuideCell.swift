//
//  WalletGuideCell.swift
//  Cosmostation
//
//  Created by yongjoo on 27/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class WalletGuideCell: UITableViewCell {
    
    @IBOutlet weak var guideImg: UIImageView!
    @IBOutlet weak var guideTitle: UILabel!
    @IBOutlet weak var guideMsg: UILabel!
    @IBOutlet weak var btn1Label: UIButton!
    @IBOutlet weak var btn2Label: UIButton!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.selectionStyle = .none
    }
    
    var actionGuide1: (() -> Void)? = nil
    var actionGuide2: (() -> Void)? = nil
    
    @IBAction func onClickGuide1(_ sender: Any) {
        actionGuide1?()
    }
    @IBAction func onClickGuide2(_ sender: Any) {
        actionGuide2?()
    }
    
    func updateView(_ account: Account?, _ chainType: ChainType?) {
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.COSMOS_TEST) {
            guideImg.image = UIImage(named: "guideImg")
            guideTitle.text = NSLocalizedString("send_guide_title_cosmos", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_cosmos", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_cosmos", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_cosmos", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.IRIS_MAIN || chainType == ChainType.IRIS_TEST) {
            guideImg.image = UIImage(named: "irisnetImg")
            guideTitle.text = NSLocalizedString("send_guide_title_iris", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_iris", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_iris", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_iris", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.AKASH_MAIN) {
            guideImg.image = UIImage(named: "akashImg")
            guideTitle.text = NSLocalizedString("send_guide_title_akash", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_akash", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_akash", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_akash", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.PERSIS_MAIN) {
            guideImg.image = UIImage(named: "persistenceImg")
            guideTitle.text = NSLocalizedString("send_guide_title_persis", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_persis", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_persis", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_persis", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.SENTINEL_MAIN) {
            guideImg.image = UIImage(named: "sentinelImg")
            guideTitle.text = NSLocalizedString("send_guide_title_sentinel", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_sentinel", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_sentinel", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_sentinel", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.CERTIK_MAIN || chainType == ChainType.CERTIK_TEST) {
            guideImg.image = UIImage(named: "certikImg")
            guideTitle.text = NSLocalizedString("send_guide_title_certik", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_certik", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_certik", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_certik", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.SECRET_MAIN) {
            guideImg.image = UIImage(named: "secretImg")
            guideTitle.text = NSLocalizedString("send_guide_title_secret", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_secret", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_secret", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_secret", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
            guideImg.image = UIImage(named: "iovImg")
            guideTitle.text = NSLocalizedString("send_guide_title_iov", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_iov", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_iov", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_iov", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.BAND_MAIN) {
            guideImg.image = UIImage(named: "bandprotocolImg")
            guideTitle.text = NSLocalizedString("send_guide_title_band", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_band", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_band", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_band", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            guideImg.image = UIImage(named: "kavamainImg")
            guideTitle.text = NSLocalizedString("send_guide_title_kava", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_kava", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_kava", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_kava", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            guideImg.image = UIImage(named: "binanceImg")
            guideTitle.text = NSLocalizedString("send_guide_title_bnb", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_bnb", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_bnb", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_bnb", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            guideImg.image = UIImage(named: "okexImg")
            guideTitle.text = NSLocalizedString("send_guide_title_ok", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_ok", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_ok", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_ok", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.FETCH_MAIN) {
            guideImg.image = UIImage(named: "fetchaiImg")
            guideTitle.text = NSLocalizedString("send_guide_title_fetch", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_fetch", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_fetch", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_fetch", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.CRYPTO_MAIN) {
            guideImg.image = UIImage(named: "cryptochainImg")
            guideTitle.text = NSLocalizedString("send_guide_title_crypto", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_crypto", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_crypto", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_crypto", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.SIF_MAIN) {
            guideImg.image = UIImage(named: "sifchainImg")
            guideTitle.text = NSLocalizedString("send_guide_title_sif", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_sif", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_sif", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_sif", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.KI_MAIN) {
            guideImg.image = UIImage(named: "kifoundationImg")
            guideTitle.text = NSLocalizedString("send_guide_title_ki", comment: "")
            guideMsg.text = NSLocalizedString("send_guide_msg_ki", comment: "")
            btn1Label.setTitle(NSLocalizedString("send_guide_btn1_ki", comment: ""), for: .normal)
            btn2Label.setTitle(NSLocalizedString("send_guide_btn2_ki", comment: ""), for: .normal)
            
        } else if (chainType == ChainType.RIZON_TEST) {
           guideImg.image = UIImage(named: "icGuideRizon")
           guideTitle.text = NSLocalizedString("send_guide_title_rizon", comment: "")
           guideMsg.text = NSLocalizedString("send_guide_msg_rizon", comment: "")
           btn1Label.setTitle(NSLocalizedString("send_guide_btn1_rizon", comment: ""), for: .normal)
           btn2Label.setTitle(NSLocalizedString("send_guide_btn2_rizon", comment: ""), for: .normal)
       }
        
    }
}
