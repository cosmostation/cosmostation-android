//
//  StepOkVoteToViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class StepOkVoteToViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var okToValidatorTableView: UITableView!
    @IBOutlet weak var toValCnt: UILabel!
    @IBOutlet weak var cancelBtn: UIButton!
    @IBOutlet weak var nextBtn: UIButton!
    
    
    var pageHolderVC: StepGenTxViewController!
    var mAllValidator = Array<Validator>()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.pageHolderVC = self.parent as? StepGenTxViewController
        self.mAllValidator = BaseData.instance.mAllValidator
        self.sortOkValidator()
        self.toValCnt.text = String(self.pageHolderVC.mOkVoteValidators.count)
        self.okToValidatorTableView.delegate = self
        self.okToValidatorTableView.dataSource = self
        self.okToValidatorTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.okToValidatorTableView.register(UINib(nibName: "RedelegateCell", bundle: nil), forCellReuseIdentifier: "RedelegateCell")
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.mAllValidator .count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:RedelegateCell? = tableView.dequeueReusableCell(withIdentifier:"RedelegateCell") as? RedelegateCell
        let validator = mAllValidator[indexPath.row]
        cell?.valMonikerLabel.text = validator.description.moniker
        if(validator.jailed) {
            cell?.valjailedImg.isHidden = false
            cell?.valjailedImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
        } else {
            cell?.valjailedImg.isHidden = true
            cell?.valjailedImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        }
        if (validator.description.identity.starts(with: "logo|||")) {
            let url = validator.description.identity.replacingOccurrences(of: "logo|||", with: "").trimmingCharacters(in: .whitespaces)
            cell?.valImg.af_setImage(withURL: URL(string: url)!)
        }
        cell?.valPowerLabel.attributedText =  WUtils.displayAmount2(validator.delegator_shares, cell!.valPowerLabel.font, 0, 0)
        cell?.valCommissionLabel.attributedText = WUtils.displayCommission("0", font: cell!.valCommissionLabel.font)
        
        cell?.rootCard.needBorderUpdate = false
        if (self.pageHolderVC.mOkVoteValidators.contains(where: {$0 == validator.operator_address})) {
            cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
            cell?.valCheckedImg.tintColor = COLOR_OK
            cell?.rootCard.backgroundColor = UIColor.clear
            cell?.rootCard.layer.borderWidth = 1
            cell?.rootCard.layer.borderColor = UIColor(hexString: "#7A8388").cgColor
            cell?.rootCard.clipsToBounds = true
            
        } else {
            cell?.valCheckedImg.image = UIImage.init(named: "checkOff")
            cell?.rootCard.backgroundColor = UIColor.init(hexString: "2E2E2E", alpha: 0.4)
            cell?.rootCard.layer.borderWidth = 0
            cell?.rootCard.clipsToBounds = true
            
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let validator = mAllValidator[indexPath.row]
        if (self.pageHolderVC.mOkVoteValidators.contains(where: {$0 == validator.operator_address})) {
            if (self.pageHolderVC.mOkVoteValidators.count == 1) {
                self.onShowToast(NSLocalizedString("error_min_1_validator", comment: ""))
                return
            }
            if let index = self.pageHolderVC.mOkVoteValidators.firstIndex(of: validator.operator_address) {
                self.pageHolderVC.mOkVoteValidators.remove(at: index)
            }
            
        } else {
            if (self.pageHolderVC.mOkVoteValidators.count > 29) {
                self.onShowToast(NSLocalizedString("error_max_30_validator", comment: ""))
                return
            }
            self.pageHolderVC.mOkVoteValidators.append(validator.operator_address)
        }
        self.toValCnt.text = String(self.pageHolderVC.mOkVoteValidators.count)
        self.okToValidatorTableView.reloadRows(at: [indexPath], with: .none)
    }
    
    override func enableUserInteraction() {
        self.cancelBtn.isUserInteractionEnabled = true
        self.nextBtn.isUserInteractionEnabled = true
    }
    
    
    @IBAction func onClickBack(_ sender: UIButton) {
        self.cancelBtn.isUserInteractionEnabled = false
        self.nextBtn.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        self.cancelBtn.isUserInteractionEnabled = false
        self.nextBtn.isUserInteractionEnabled = false
        pageHolderVC.onNextPage()
    }

    
    func sortOkValidator() {
        self.mAllValidator.sort{
            if ($0.description.moniker == "Cosmostation") {
                return true
            }
            if ($1.description.moniker == "Cosmostation"){
                return false
            }
            if ($0.jailed && !$1.jailed) {
                return false
            }
            if (!$0.jailed && $1.jailed) {
                return true
            }
            return Double($0.delegator_shares)! > Double($1.delegator_shares)!
        }
    }
}
