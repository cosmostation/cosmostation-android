//
//  StepRedelegateToViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 23/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage

class StepRedelegateToViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var redelegateToValTableView: UITableView!
    @IBOutlet weak var btnBefore: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    var pageHolderVC: StepGenTxViewController!
    var checkedValidator: Validator?
    var checkedPosition:IndexPath?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        pageHolderVC = self.parent as? StepGenTxViewController
        self.redelegateToValTableView.delegate = self
        self.redelegateToValTableView.dataSource = self
        self.redelegateToValTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.redelegateToValTableView.register(UINib(nibName: "RedelegateCell", bundle: nil), forCellReuseIdentifier: "RedelegateCell")
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.pageHolderVC.mToReDelegateValidators.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:RedelegateCell? = tableView.dequeueReusableCell(withIdentifier:"RedelegateCell") as? RedelegateCell
        if let validator = self.pageHolderVC.mToReDelegateValidators[indexPath.row] as? Validator {
            cell?.valMonikerLabel.text = validator.description.moniker
            if(validator.jailed) {
                cell?.valjailedImg.isHidden = false
                cell?.valjailedImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
            } else {
                cell?.valjailedImg.isHidden = true
                cell?.valjailedImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
            }
            
            if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                cell?.valPowerLabel.attributedText  =  WUtils.displayAmount(validator.tokens, cell!.valPowerLabel.font, 6, pageHolderVC.userChain!)
                if (self.pageHolderVC.mStakingPool != nil && self.pageHolderVC.mProvision != nil) {
                    let provisions = NSDecimalNumber.init(string: self.pageHolderVC.mProvision)
                    let bonded_tokens = NSDecimalNumber.init(string: self.pageHolderVC.mStakingPool?.object(forKey: "bonded_tokens") as! String)
                    cell?.valCommissionLabel.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: validator.commission.rate), font: cell!.valCommissionLabel.font)
                } else {
                    cell?.valCommissionLabel.text = "?? %"
                }
                
            } else if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                cell?.valPowerLabel.attributedText  =  WUtils.displayAmount(NSDecimalNumber.init(string: validator.tokens).multiplying(byPowerOf10: 18, withBehavior: WUtils.handler0).stringValue, cell!.valPowerLabel.font, 6, pageHolderVC.userChain!)
                if (self.pageHolderVC.mIrisStakePool != nil) {
                    let provisions = NSDecimalNumber.init(string: self.pageHolderVC.mIrisStakePool?.object(forKey: "total_supply") as? String).multiplying(by: NSDecimalNumber.init(string: "0.04"))
                    let bonded_tokens = NSDecimalNumber.init(string: self.pageHolderVC.mIrisStakePool?.object(forKey: "bonded_tokens") as? String)
                    cell?.valCommissionLabel.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: validator.commission.rate), font: (cell?.valCommissionLabel.font)!)
                } else {
                    cell?.valCommissionLabel.text = "-"
                }
            }

            cell?.valImg.tag = indexPath.row
            cell?.valImg.image = UIImage.init(named: "validatorNoneImg")
            if (validator.description.identity != "") {
                let parameters: Parameters = ["fields": "pictures", "key_suffix": validator.description.identity]
                let request = Alamofire.request(KEY_BASE_URL_USER_INFO,
                                                method: .get,
                                                parameters: parameters,
                                                encoding: URLEncoding.default,
                                                headers: [:]);
                request.responseJSON { (response) in
                    switch response.result {
                    case .success(let res):
//                        print("res : ", res)
                        guard let keybaseInfo = res as? NSDictionary,
                            let thems = keybaseInfo.value(forKey: "them") as? Array<NSDictionary>,
                            thems.count > 0,
                            let url = thems[0].value(forKeyPath: "pictures.primary.url") as? String else {
                                return
                        }
                        Alamofire.request(url, method: .get).responseImage { response  in
                            guard let image = response.result.value else {
                                return
                            }
                            if(indexPath.row == cell?.valImg.tag) {
                                cell?.valImg.image = image
                            }
                        }

                    case .failure(let error):
                        print("onSetValidatorItem error : ", error)
                    }
                }
            }

            cell?.rootCard.needBorderUpdate = false
            if (validator.operator_address == checkedValidator?.operator_address && pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_ATOM
                cell?.rootCard.backgroundColor = UIColor.clear
                cell?.rootCard.layer.borderWidth = 1
                cell?.rootCard.layer.borderColor = UIColor(hexString: "#7A8388").cgColor
                cell?.rootCard.clipsToBounds = true

            } else if (validator.operator_address == checkedValidator?.operator_address && pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_IRIS
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

        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if let validator = self.pageHolderVC.mToReDelegateValidators[indexPath.row] as? Validator {
            self.checkedValidator = validator
            self.checkedPosition = indexPath
            let cell:RedelegateCell? = tableView.cellForRow(at: indexPath) as? RedelegateCell
            cell?.rootCard.needBorderUpdate = false
            if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_ATOM
            } else if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_IRIS
            }
            cell?.rootCard.backgroundColor = UIColor.clear
            cell?.rootCard.layer.borderWidth = 1
            cell?.rootCard.layer.borderColor = UIColor(hexString: "#7A8388").cgColor
            cell?.rootCard.clipsToBounds = true
        }
    }
    
    func tableView(_ tableView: UITableView, didDeselectRowAt indexPath: IndexPath) {
        let cell:RedelegateCell? = tableView.cellForRow(at: indexPath) as? RedelegateCell
        cell?.valCheckedImg.image = UIImage.init(named: "checkOff")
        cell?.rootCard.backgroundColor = UIColor.init(hexString: "2E2E2E", alpha: 0.4)
        cell?.rootCard.layer.borderWidth = 0
        cell?.rootCard.clipsToBounds = true
    }
    
    override func enableUserInteraction() {
        self.btnBefore.isUserInteractionEnabled = true
        self.btnNext.isUserInteractionEnabled = true
        if(self.checkedPosition != nil) {
            self.redelegateToValTableView.selectRow(at: checkedPosition, animated: false, scrollPosition: .middle)
        }
        self.checkedValidator = pageHolderVC.mToReDelegateValidator
    }
    
    @IBAction func onClickBack(_ sender: UIButton) {
        self.btnBefore.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onClickNext(_ sender: UIButton) {
        if(self.checkedValidator != nil && self.checkedValidator?.operator_address != nil) {
            if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                self.onFetchRedelegateState(pageHolderVC.mAccount!.account_address, pageHolderVC.mTargetValidator!.operator_address, self.checkedValidator!.operator_address)
                
            } else if (pageHolderVC.userChain! == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                if (self.pageHolderVC.mirisRedelegate != nil) {
                    for irisRedelegate in (self.pageHolderVC?.mirisRedelegate)! {
                        if let fromAdd = irisRedelegate.value(forKey: "validator_src_addr") as? String,
                            let toAdd = irisRedelegate.value(forKey: "validator_dst_addr") as? String,
                            fromAdd == self.pageHolderVC.mTargetValidator?.operator_address,
                            toAdd == self.checkedValidator?.operator_address {
                            self.onShowToast(NSLocalizedString("error_redelegate_cnt_over", comment: ""))
                            return
                        }
                    }
                }
                self.goNextPage()
                
            }
        } else {
            self.onShowToast(NSLocalizedString("error_redelegate_no_to_address", comment: ""))
        }
    }
    
    func goNextPage() {
        self.btnBefore.isUserInteractionEnabled = false
        self.btnNext.isUserInteractionEnabled = false
        pageHolderVC.mToReDelegateValidator = self.checkedValidator
        pageHolderVC.onNextPage()
    }
    
    
    func onFetchRedelegateState(_ address: String, _ from: String, _ to: String) {
        let request = Alamofire.request(CSS_LCD_URL_REDELEGATION, method: .get, parameters: ["delegator":address, "validator_from":from, "validator_to":to], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if let clearResult = res as? NSDictionary, let msg = clearResult["error"] as? String {
                    if(clearResult["error"] != nil && msg.contains("no redelegation found")) {
                        self.goNextPage()
                    }
                }
                if let redelegateHistories = res as? Array<NSDictionary>, let entries = redelegateHistories[0]["entries"] as? Array<NSDictionary> {
                    if(entries.count >= 7) {
                        self.onShowToast(NSLocalizedString("error_redelegate_cnt_over", comment: ""))
                    } else {
                        self.goNextPage()
                    }
                }
                
            case .failure(let error):
                print("onFetchRedelegateState ", error)
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
}
