//
//  StepRedelegateToViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 23/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire

class StepRedelegateToViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var redelegateToValTableView: UITableView!
    @IBOutlet weak var btnBefore: UIButton!
    @IBOutlet weak var btnNext: UIButton!
    
    var mProvision: String?
    var mStakingPool: NSDictionary?
    var mIrisStakePool: NSDictionary?
    
    var pageHolderVC: StepGenTxViewController!
    var checkedValidator: Validator?
    var checkedPosition:IndexPath?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.mProvision = BaseData.instance.mProvision
        self.mStakingPool = BaseData.instance.mStakingPool
        self.mIrisStakePool = BaseData.instance.mIrisStakePool
        
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
            
            if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN) {
                cell?.valPowerLabel.attributedText  =  WUtils.displayAmount2(validator.tokens, cell!.valPowerLabel.font, 6, 6)
                cell?.valCommissionLabel.attributedText = WUtils.getDpEstAprCommission(cell!.valCommissionLabel.font, validator.getCommission(), pageHolderVC.chainType!)
                cell!.valImg.af_setImage(withURL: URL(string: COSMOS_VAL_URL + validator.operator_address + ".png")!)
                
            } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
                cell?.valPowerLabel.attributedText  =  WUtils.displayAmount2(NSDecimalNumber.init(string: validator.tokens).multiplying(byPowerOf10: 18, withBehavior: WUtils.handler0).stringValue, cell!.valPowerLabel.font, 18, 18)
                if (self.mIrisStakePool != nil) {
                    let provisions = NSDecimalNumber.init(string: self.mIrisStakePool?.object(forKey: "total_supply") as? String).multiplying(by: NSDecimalNumber.init(string: "0.04"))
                    let bonded_tokens = NSDecimalNumber.init(string: self.mIrisStakePool?.object(forKey: "bonded_tokens") as? String)
                    cell?.valCommissionLabel.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: validator.commission.rate), font: (cell?.valCommissionLabel.font)!)
                } else {
                    cell?.valCommissionLabel.text = "-"
                }
                cell!.valImg.af_setImage(withURL: URL(string: IRIS_VAL_URL + validator.operator_address + ".png")!)
                
            } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                cell?.valPowerLabel.attributedText  =  WUtils.displayAmount2(validator.tokens, cell!.valPowerLabel.font, 6, 6)
                cell?.valCommissionLabel.attributedText = WUtils.getDpEstAprCommission(cell!.valCommissionLabel.font, validator.getCommission(), pageHolderVC.chainType!)
                cell!.valImg.af_setImage(withURL: URL(string: KAVA_VAL_URL + validator.operator_address + ".png")!)
                
            } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
                cell?.valPowerLabel.attributedText  =  WUtils.displayAmount2(validator.tokens, cell!.valPowerLabel.font, 6, 6)
                cell?.valCommissionLabel.attributedText = WUtils.getDpEstAprCommission(cell!.valCommissionLabel.font, validator.getCommission(), pageHolderVC.chainType!)
                cell!.valImg.af_setImage(withURL: URL(string: BAND_VAL_URL + validator.operator_address + ".png")!)
                
            } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
                cell?.valPowerLabel.attributedText = WUtils.displayAmount2(validator.tokens, cell!.valPowerLabel.font, 6, 6)
                cell?.valCommissionLabel.attributedText = WUtils.getDpEstAprCommission(cell!.valCommissionLabel.font, validator.getCommission(), pageHolderVC.chainType!)
                cell!.valImg.af_setImage(withURL: URL(string: SECRET_VAL_URL + validator.operator_address + ".png")!)
                
            } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST) {
                cell?.valPowerLabel.attributedText  =  WUtils.displayAmount2(validator.tokens, cell!.valPowerLabel.font, 6, 6)
                cell?.valCommissionLabel.attributedText = WUtils.getDpEstAprCommission(cell!.valCommissionLabel.font, validator.getCommission(), pageHolderVC.chainType!)
                cell!.valImg.af_setImage(withURL: URL(string: IOV_VAL_URL + validator.operator_address + ".png")!)
                
            } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
                cell?.valPowerLabel.attributedText  =  WUtils.displayAmount2(validator.tokens, cell!.valPowerLabel.font, 6, 6)
                cell?.valCommissionLabel.attributedText = WUtils.getDpEstAprCommission(cell!.valCommissionLabel.font, validator.getCommission(), pageHolderVC.chainType!)
                cell!.valImg.af_setImage(withURL: URL(string: CERTIK_VAL_URL + validator.operator_address + ".png")!)
                
            } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
                cell?.valPowerLabel.attributedText  =  WUtils.displayAmount2(validator.tokens, cell!.valPowerLabel.font, 6, 6)
                cell?.valCommissionLabel.attributedText = WUtils.getDpEstAprCommission(cell!.valCommissionLabel.font, validator.getCommission(), pageHolderVC.chainType!)
                cell!.valImg.af_setImage(withURL: URL(string: AKASH_VAL_URL + validator.operator_address + ".png")!)
                
            }

            cell?.rootCard.needBorderUpdate = false
            if (validator.operator_address == checkedValidator?.operator_address && pageHolderVC.chainType! == ChainType.COSMOS_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_ATOM
                cell?.rootCard.backgroundColor = UIColor.clear
                cell?.rootCard.layer.borderWidth = 1
                cell?.rootCard.layer.borderColor = UIColor(hexString: "#7A8388").cgColor
                cell?.rootCard.clipsToBounds = true

            } else if (validator.operator_address == checkedValidator?.operator_address && pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_IRIS
                cell?.rootCard.backgroundColor = UIColor.clear
                cell?.rootCard.layer.borderWidth = 1
                cell?.rootCard.layer.borderColor = UIColor(hexString: "#7A8388").cgColor
                cell?.rootCard.clipsToBounds = true
                
            } else if (validator.operator_address == checkedValidator?.operator_address &&
                        (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST)) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_KAVA
                cell?.rootCard.backgroundColor = UIColor.clear
                cell?.rootCard.layer.borderWidth = 1
                cell?.rootCard.layer.borderColor = UIColor(hexString: "#7A8388").cgColor
                cell?.rootCard.clipsToBounds = true
                
            } else if (validator.operator_address == checkedValidator?.operator_address && pageHolderVC.chainType! == ChainType.BAND_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_BAND
                cell?.rootCard.backgroundColor = UIColor.clear
                cell?.rootCard.layer.borderWidth = 1
                cell?.rootCard.layer.borderColor = UIColor(hexString: "#7A8388").cgColor
                cell?.rootCard.clipsToBounds = true
                
            } else if (validator.operator_address == checkedValidator?.operator_address && pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_SECRET
                cell?.rootCard.backgroundColor = UIColor.clear
                cell?.rootCard.layer.borderWidth = 1
                cell?.rootCard.layer.borderColor = UIColor(hexString: "#7A8388").cgColor
                cell?.rootCard.clipsToBounds = true
                
            } else if (validator.operator_address == checkedValidator?.operator_address &&
                (pageHolderVC.chainType! == ChainType.IOV_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST)) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_IOV
                cell?.rootCard.backgroundColor = UIColor.clear
                cell?.rootCard.layer.borderWidth = 1
                cell?.rootCard.layer.borderColor = UIColor(hexString: "#7A8388").cgColor
                cell?.rootCard.clipsToBounds = true
                
            } else if (validator.operator_address == checkedValidator?.operator_address &&
                            (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST)) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_CERTIK
                cell?.rootCard.backgroundColor = UIColor.clear
                cell?.rootCard.layer.borderWidth = 1
                cell?.rootCard.layer.borderColor = UIColor(hexString: "#7A8388").cgColor
                cell?.rootCard.clipsToBounds = true
                
            }  else if (validator.operator_address == checkedValidator?.operator_address && pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_AKASH
                cell?.rootCard.backgroundColor = UIColor.clear
                cell?.rootCard.layer.borderWidth = 1
                cell?.rootCard.layer.borderColor = UIColor(hexString: "#7A8388").cgColor
                cell?.rootCard.clipsToBounds = true
                
            }
            
            else {
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
            if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_ATOM
            } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_IRIS
            } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_KAVA
            } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_BAND
            } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_SECRET
            } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN || pageHolderVC.chainType! == ChainType.IOV_TEST) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_IOV
            } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_CERTIK
            } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
                cell?.valCheckedImg.image = cell?.valCheckedImg.image?.withRenderingMode(.alwaysTemplate)
                cell?.valCheckedImg.tintColor = COLOR_AKASH
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
            if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN || pageHolderVC.chainType! == ChainType.KAVA_MAIN || pageHolderVC.chainType! == ChainType.KAVA_TEST ||
                    pageHolderVC.chainType! == ChainType.BAND_MAIN || pageHolderVC.chainType! == ChainType.SECRET_MAIN || pageHolderVC.chainType! == ChainType.IOV_MAIN ||
                    pageHolderVC.chainType! == ChainType.IOV_TEST || pageHolderVC.chainType! == ChainType.CERTIK_MAIN || pageHolderVC.chainType! == ChainType.CERTIK_TEST ||
                    pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
                self.onFetchRedelegateState(pageHolderVC.mAccount!.account_address, pageHolderVC.mTargetValidator!.operator_address, self.checkedValidator!.operator_address)
                
            } else if (pageHolderVC.chainType! == ChainType.IRIS_MAIN) {
                if (self.pageHolderVC.mIrisRedelegate != nil) {
                    for irisRedelegate in (self.pageHolderVC?.mIrisRedelegate)! {
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
        var url: String?
        if (pageHolderVC.chainType! == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_REDELEGATION;
        } else if (pageHolderVC.chainType! == ChainType.KAVA_MAIN) {
            url = KAVA_REDELEGATION;
        } else if (pageHolderVC.chainType! == ChainType.KAVA_TEST) {
            url = KAVA_TEST_REDELEGATION;
        } else if (pageHolderVC.chainType! == ChainType.BAND_MAIN) {
            url = BAND_REDELEGATION;
        } else if (pageHolderVC.chainType! == ChainType.SECRET_MAIN) {
            url = SECRET_REDELEGATION;
        } else if (pageHolderVC.chainType! == ChainType.IOV_MAIN) {
            url = IOV_REDELEGATION;
        } else if (pageHolderVC.chainType! == ChainType.IOV_TEST) {
            url = IOV_TEST_REDELEGATION;
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_MAIN) {
            url = CERTIK_REDELEGATION;
        } else if (pageHolderVC.chainType! == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_REDELEGATION;
        } else if (pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
            url = AKASH_REDELEGATION;
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["delegator":address, "validator_from":from, "validator_to":to], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                print("res ", res)
                if (self.pageHolderVC.chainType! == ChainType.COSMOS_MAIN || self.pageHolderVC.chainType! == ChainType.KAVA_MAIN || self.pageHolderVC.chainType! == ChainType.KAVA_TEST ||
                        self.pageHolderVC.chainType! == ChainType.BAND_MAIN || self.pageHolderVC.chainType! == ChainType.SECRET_MAIN || self.pageHolderVC.chainType! == ChainType.IOV_MAIN ||
                        self.pageHolderVC.chainType! == ChainType.IOV_TEST || self.pageHolderVC.chainType! == ChainType.CERTIK_MAIN || self.pageHolderVC.chainType! == ChainType.CERTIK_TEST ||
                        self.pageHolderVC.chainType! == ChainType.AKASH_MAIN) {
                    if let clearResult = res as? NSDictionary, let msg = clearResult["error"] as? String {
                        if(clearResult["error"] != nil && msg.contains("no redelegation found")) {
                            self.goNextPage()
                            return
                        }
                    }
                    if let responseData = res as? NSDictionary,
                        let redelegateHistories = responseData.object(forKey: "result") as? Array<NSDictionary> {
                        if (redelegateHistories.count >= 7) {
                            self.onShowToast(NSLocalizedString("error_redelegate_cnt_over", comment: ""))
                        } else {
                            self.goNextPage()
                        }
                    } else {
                        self.onShowToast(NSLocalizedString("error_network", comment: ""))
                    }
                }
                
            case .failure(let error):
                print("onFetchRedelegateState ", error)
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
}
