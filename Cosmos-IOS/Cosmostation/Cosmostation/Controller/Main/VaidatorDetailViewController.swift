//
//  VaidatorDetailViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 04/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SafariServices

class VaidatorDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var chainBg: UIImageView!
    @IBOutlet weak var validatorDetailTableView: UITableView!
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    var mAccount: Account?
    var userChain: ChainType?
    var mValidator: Validator?
    var mBonding: Bonding?
    var mUnbondings = Array<Unbonding>()
    var mRewards = Array<Reward>()
    var mIrisRewards: IrisRewards?
    var mHistories = Array<History.InnerHits>()
    var mSelfBondingShare: String?
    var mFetchCnt = 0
    var mMyValidator = false
    var mIsTop100 = false
    
    var mInflation: String?
    var mProvision: String?
    var mStakingPool: NSDictionary?
    var mIrisStakePool: NSDictionary?

    override func viewDidLoad() {
        super.viewDidLoad()
        mAccount = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        userChain = WUtils.getChainType(mAccount!.account_base_chain)
        
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            chainBg.image = UIImage(named: "bg_cosmos")
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            chainBg.image = UIImage(named: "bg_iris")
        }
        
        self.validatorDetailTableView.delegate = self
        self.validatorDetailTableView.dataSource = self
        self.validatorDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.validatorDetailTableView.register(UINib(nibName: "ValidatorDetailMyDetailCell", bundle: nil), forCellReuseIdentifier: "ValidatorDetailMyDetailCell")
        self.validatorDetailTableView.register(UINib(nibName: "ValidatorDetailMyActionCell", bundle: nil), forCellReuseIdentifier: "ValidatorDetailMyActionCell")
        self.validatorDetailTableView.register(UINib(nibName: "ValidatorDetailCell", bundle: nil), forCellReuseIdentifier: "ValidatorDetailCell")
        self.validatorDetailTableView.register(UINib(nibName: "ValidatorDetailHistoryEmpty", bundle: nil), forCellReuseIdentifier: "ValidatorDetailHistoryEmpty")
        self.validatorDetailTableView.register(UINib(nibName: "HistoryCell", bundle: nil), forCellReuseIdentifier: "HistoryCell")
        
        self.loadingImg.onStartAnimation()
        self.onFech()
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_validator_detail", comment: "")
        self.navigationItem.title = NSLocalizedString("title_validator_detail", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
        
    }
    
    func onFech(){
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            mUnbondings.removeAll()
            mRewards.removeAll()
            mFetchCnt = 5
            onFetchValidatorInfo(mValidator!)
            onFetchSignleBondingInfo(mAccount!, mValidator!)
            onFetchSignleUnBondingInfo(mAccount!, mValidator!)
            onFetchSelfBondRate(WKey.getAddressFromOpAddress(mValidator!.operator_address, userChain!), mValidator!.operator_address)
            onFetchHistory(mAccount!, mValidator!, "0", "100");
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            mUnbondings.removeAll()
            mFetchCnt = 6
            onFetchValidatorInfo(mValidator!)
            onFetchSignleBondingInfo(mAccount!, mValidator!)
            onFetchSignleUnBondingInfo(mAccount!, mValidator!)
            onFetchSelfBondRate(WKey.getAddressFromOpAddress(mValidator!.operator_address, userChain!), mValidator!.operator_address)
            onFetchIrisReward(mAccount!)
            onFetchHistory(mAccount!, mValidator!, "0", "100");
        }
        
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
//        print("onFetchFinished ", self.mFetchCnt)
        if(mFetchCnt <= 0) {
            print("onFetchFinished mBonding ", mBonding)
            print("onFetchFinished mBonding Amount ", mBonding?.getBondingAmount(mValidator!))
            print("onFetchFinished mBonding Amount stringValue", mBonding?.getBondingAmount(mValidator!).stringValue)
            print("onFetchFinished mBonding Share ", mBonding?.bonding_shares)
            print("onFetchFinished mUnbondings ", mUnbondings.count)
            print("onFetchFinished mRewards ", mRewards.count)
            print("onFetchFinished mHistories ", mHistories.count)

            if((mBonding != nil && mBonding?.getBondingAmount(mValidator!) != NSDecimalNumber.zero) || mUnbondings.count > 0) {
                mMyValidator = true
            } else {
                mMyValidator = false
            }
//            print("mMyValidator ", mMyValidator)
            
            self.validatorDetailTableView.reloadData()
            
            self.loadingImg.onStopAnimation()
            self.loadingImg.isHidden = true
            self.validatorDetailTableView.isHidden = false
            
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            if(mMyValidator) {
                return 2
            } else {
                return 1
            }
            
        } else {
            if(mHistories.count > 0) {
                return mHistories.count
            } else {
                return 1
            }
        }
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let view = ValidatorDetailHistoryHeader(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        return view
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if(indexPath.section == 0) {
            if (indexPath.row == 0 && mMyValidator) {
                let cell:ValidatorDetailMyDetailCell? = tableView.dequeueReusableCell(withIdentifier:"ValidatorDetailMyDetailCell") as? ValidatorDetailMyDetailCell
                cell?.cardView.backgroundColor = WUtils.getChainBg(userChain!)
                cell?.monikerName.text = self.mValidator!.description.moniker
                cell?.monikerName.adjustsFontSizeToFitWidth = true
                if(self.mValidator!.jailed) {
                    cell!.jailedImg.isHidden = false
                    cell!.validatorImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
                } else {
                    cell!.jailedImg.isHidden = true
                    cell!.validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
                }
                cell?.freeEventImg.isHidden = true
                cell?.website.text = mValidator!.description.website
                cell?.descriptionMsg.text = mValidator!.description.details
                
                if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    cell?.totalBondedAmount.attributedText =  WUtils.displayAmout(mValidator!.tokens, cell!.totalBondedAmount.font, 6)
                } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    cell?.totalBondedAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.init(string: mValidator!.tokens).multiplying(byPowerOf10: 18, withBehavior: WUtils.handler0).stringValue, cell!.totalBondedAmount.font, 18, userChain!)
                }
                
                if (mSelfBondingShare != nil) {
                    cell!.selfBondedRate.attributedText = WUtils.displaySelfBondRate(mSelfBondingShare!, mValidator!.tokens, cell!.selfBondedRate.font)
                } else {
                    cell!.selfBondedRate.attributedText = WUtils.displaySelfBondRate(NSDecimalNumber.zero.stringValue, mValidator!.tokens, cell!.selfBondedRate.font)
                }
                
                if (mIsTop100 && userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    if(mStakingPool != nil && mProvision != nil) {
                        let provisions = NSDecimalNumber.init(string: mProvision)
                        let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                        cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.rate), font: cell!.avergaeYield.font)
                    } else {
                        cell!.avergaeYield.text = "?? %"
                    }
                } else if (mIsTop100 && userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    if (mIrisStakePool != nil) {
                        let provisions = NSDecimalNumber.init(string: mIrisStakePool?.object(forKey: "total_supply") as? String).multiplying(by: NSDecimalNumber.init(string: "0.04"))
                        let bonded_tokens = NSDecimalNumber.init(string: mIrisStakePool?.object(forKey: "bonded_tokens") as? String)
                        cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.rate), font: cell!.avergaeYield.font)
                    } else {
                        cell!.avergaeYield.text = "?? %"
                    }
                } else {
                    cell!.avergaeYield.attributedText = WUtils.displayCommission(NSDecimalNumber.zero.stringValue, font: cell!.avergaeYield.font)
                    cell!.avergaeYield.textColor = UIColor.init(hexString: "f31963")
                }
                
                cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.rate, font: cell!.commissionRate.font)
                if (mValidator!.description.identity != "") {
                    let parameters: Parameters = ["fields": "pictures", "key_suffix": mValidator!.description.identity]
                    let request = Alamofire.request(KEY_BASE_URL_USER_INFO,
                                                    method: .get,
                                                    parameters: parameters,
                                                    encoding: URLEncoding.default,
                                                    headers: [:]);
                    request.responseJSON { (response) in
                        switch response.result {
                        case .success(let res):
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
                                cell!.validatorImg.image = image
                            }
                            
                        case .failure(let error):
                            print("onSetValidatorItem error : ", error)
                        }
                    }
                }
                return cell!
                
            } else if (indexPath.row == 0 && !mMyValidator) {
                let cell:ValidatorDetailCell? = tableView.dequeueReusableCell(withIdentifier:"ValidatorDetailCell") as? ValidatorDetailCell
                cell?.monikerName.text = self.mValidator!.description.moniker
                cell?.monikerName.adjustsFontSizeToFitWidth = true
                if(self.mValidator!.jailed) {
                    cell?.jailedImg.isHidden = false
                    cell?.validatorImg.layer.borderColor = UIColor(hexString: "#f31963").cgColor
                } else {
                    cell?.jailedImg.isHidden = true
                    cell?.validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
                }
                cell?.freeEventImg.isHidden = true
                cell?.website.text = mValidator!.description.website
                cell?.descriptionMsg.text = mValidator!.description.details
                
                if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    cell?.totalBondedAmount.attributedText =  WUtils.displayAmout(mValidator!.tokens, cell!.totalBondedAmount.font, 6)
                } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    cell?.totalBondedAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.init(string: mValidator!.tokens).multiplying(byPowerOf10: 18, withBehavior: WUtils.handler0).stringValue, cell!.totalBondedAmount.font, 18, userChain!)
                }
                
                if (mSelfBondingShare != nil) {
                    cell!.selfBondedRate.attributedText = WUtils.displaySelfBondRate(mSelfBondingShare!, mValidator!.tokens, cell!.selfBondedRate.font)
                } else {
                    cell!.selfBondedRate.attributedText = WUtils.displaySelfBondRate(NSDecimalNumber.zero.stringValue, mValidator!.tokens, cell!.selfBondedRate.font)
                }
                
                if (mIsTop100 && userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    if(mStakingPool != nil && mProvision != nil) {
                        let provisions = NSDecimalNumber.init(string: mProvision)
                        let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                        cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.rate), font: cell!.avergaeYield.font)
                    } else {
                        cell!.avergaeYield.text = "?? %"
                    }
                } else if (mIsTop100 && userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    if (mIrisStakePool != nil) {
                        let provisions = NSDecimalNumber.init(string: mIrisStakePool?.object(forKey: "total_supply") as? String).multiplying(by: NSDecimalNumber.init(string: "0.04"))
                        let bonded_tokens = NSDecimalNumber.init(string: mIrisStakePool?.object(forKey: "bonded_tokens") as? String)
                        cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.rate), font: cell!.avergaeYield.font)
                    } else {
                        cell!.avergaeYield.text = "?? %"
                    }
                } else {
                    cell!.avergaeYield.attributedText = WUtils.displayCommission(NSDecimalNumber.zero.stringValue, font: cell!.avergaeYield.font)
                    cell!.avergaeYield.textColor = UIColor.init(hexString: "f31963")
                }
                
                cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.rate, font: cell!.commissionRate.font)
                if (mValidator!.description.identity != "") {
                    let parameters: Parameters = ["fields": "pictures", "key_suffix": mValidator!.description.identity]
                    let request = Alamofire.request(KEY_BASE_URL_USER_INFO,
                                                    method: .get,
                                                    parameters: parameters,
                                                    encoding: URLEncoding.default,
                                                    headers: [:]);
                    request.responseJSON { (response) in
                        switch response.result {
                        case .success(let res):
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
                                cell!.validatorImg.image = image
                            }
                            
                        case .failure(let error):
                            print("onSetValidatorItem error : ", error)
                        }
                    }
                }
                cell?.actionDelegate = {
                    if(self.mValidator!.jailed) {
                        self.onShowToast(NSLocalizedString("error_jailded_delegate", comment: ""))
                        return
                    } else {
                        self.onStartDelegate()
                    }
                }
                return cell!
                
            } else {
                let cell:ValidatorDetailMyActionCell? = tableView.dequeueReusableCell(withIdentifier:"ValidatorDetailMyActionCell") as? ValidatorDetailMyActionCell
                cell?.cardView.backgroundColor = WUtils.getChainBg(userChain!)
                
                if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    if(mBonding != nil) {
                        cell!.myDelegateAmount.attributedText =  WUtils.displayAmount((mBonding?.getBondingAmount(mValidator!).stringValue)!, cell!.myDelegateAmount.font, 6, userChain!)
                    } else {
                        cell!.myDelegateAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myDelegateAmount.font, 6, userChain!)
                    }
                    
                    if (mUnbondings.count > 0) {
                        var unbondSum = NSDecimalNumber.zero
                        for unbonding in mUnbondings {
                            unbondSum  = unbondSum.adding(WUtils.stringToDecimal(unbonding.unbonding_balance))
                        }
                        cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount(unbondSum.stringValue, cell!.myUndelegateAmount.font, 6, userChain!)
                    } else {
                        cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myUndelegateAmount.font, 6, userChain!)
                    }
                    
                    if (mRewards.count > 0) {
                        let rewardSum = WUtils.getAllAtomReward(mRewards)
                        cell!.myRewardAmount.attributedText =  WUtils.displayAmount(rewardSum.stringValue, cell!.myRewardAmount.font, 6, userChain!)
                    } else {
                        cell!.myRewardAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myRewardAmount.font, 6, userChain!)
                    }
                    
                } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    if(mBonding != nil) {
                        cell!.myDelegateAmount.attributedText =  WUtils.displayAmount((mBonding?.getBondingAmount(mValidator!).stringValue)!, cell!.myDelegateAmount.font, 18, userChain!)
                    } else {
                        cell!.myDelegateAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myDelegateAmount.font, 18, userChain!)
                    }
                    
                    if (mUnbondings.count > 0) {
                        var unbondSum = NSDecimalNumber.zero
                        for unbonding in mUnbondings {
                            unbondSum  = unbondSum.adding(WUtils.stringToDecimal(unbonding.unbonding_balance))
                        }
                        cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount(unbondSum.stringValue, cell!.myUndelegateAmount.font, 18, userChain!)
                    } else {
                        cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myUndelegateAmount.font, 18, userChain!)
                    }
                    
                    if (mIrisRewards != nil) {
                        cell!.myRewardAmount.attributedText = WUtils.displayAmount((mIrisRewards?.getPerValReward(valOp: mValidator!.operator_address).stringValue)!, cell!.myRewardAmount.font, 18, userChain!)
                    } else {
                        cell!.myRewardAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myRewardAmount.font, 18, userChain!)
                    }
                }
                
                if (mIsTop100 && userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    if (mStakingPool != nil && mProvision != nil && mBonding != nil) {
                        let provisions = NSDecimalNumber.init(string: mProvision)
                        let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                        cell!.myDailyReturns.attributedText = WUtils.displayDailyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myDailyReturns.font, baseChain: userChain!)
                        cell!.myMonthlyReturns.attributedText = WUtils.displayMonthlyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myMonthlyReturns.font, baseChain: userChain!)
                    } else {
                        cell!.myDailyReturns.text = "-"
                        cell!.myMonthlyReturns.text = "-"
                    }
                } else if (mIsTop100 && userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    if (mIrisStakePool != nil && mBonding != nil) {
                        let provisions = NSDecimalNumber.init(string: mIrisStakePool?.object(forKey: "total_supply") as? String).multiplying(by: NSDecimalNumber.init(string: "0.04"))
                        let bonded_tokens = NSDecimalNumber.init(string: mIrisStakePool?.object(forKey: "bonded_tokens") as? String)
                        cell!.myDailyReturns.attributedText = WUtils.displayDailyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myDailyReturns.font, baseChain: userChain!)
                        cell!.myMonthlyReturns.attributedText = WUtils.displayMonthlyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myMonthlyReturns.font, baseChain: userChain!)
                    } else {
                        cell!.myDailyReturns.text = "-"
                        cell!.myMonthlyReturns.text = "-"
                    }
                } else {
                    cell!.myDailyReturns.attributedText = WUtils.displayDailyReturns(NSDecimalNumber.one, NSDecimalNumber.one, NSDecimalNumber.one, NSDecimalNumber.one, font: cell!.myDailyReturns.font, baseChain: userChain!)
                    cell!.myMonthlyReturns.attributedText = WUtils.displayMonthlyReturns(NSDecimalNumber.one, NSDecimalNumber.one, NSDecimalNumber.one, NSDecimalNumber.one, font: cell!.myMonthlyReturns.font, baseChain: userChain!)
                    cell!.myDailyReturns.textColor = UIColor.init(hexString: "f31963")
                    cell!.myMonthlyReturns.textColor = UIColor.init(hexString: "f31963")
                }
                
                cell?.actionDelegate = {
                    if(self.mValidator!.jailed) {
                        self.onShowToast(NSLocalizedString("error_jailded_delegate", comment: ""))
                        return
                    } else {
                        self.onStartDelegate()
                    }
                }
                
                cell?.actionUndelegate = {
                    self.onStartUndelegate()
                }
                
                cell?.actionRedelegate = {
                    self.onCheckRedelegate()
                }
                
                cell?.actionReward = {
                    self.onStartGetSingleReward()
                }
                cell?.actionReinvest = {
                    self.onCheckReinvest()
                }
                return cell!
            }
            
        } else {
            if(mHistories.count > 0) {
                let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
                let history = mHistories[indexPath.row]
                
                cell?.txTimeLabel.text = WUtils.nodeTimetoString(input: history._source.time)
                cell?.txBlockLabel.text = String(history._source.height) + " block"
                cell?.txTimeGapLabel.text = WUtils.timeGap(input: history._source.time)
                cell?.txTypeLabel.text = WUtils.historyTitle(history._source.tx.value.msg, mAccount!.account_address)
                if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    if(history._source.result.allResult) {
                        cell?.txResultLabel.isHidden = true
                    } else {
                        cell?.txResultLabel.isHidden = false
                    }
                } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    if(history._source.result.code > 0) {
                        cell?.txResultLabel.isHidden = false
                    } else {
                        cell?.txResultLabel.isHidden = true
                    }
                }
                return cell!
            } else {
                let cell:ValidatorDetailHistoryEmpty? = tableView.dequeueReusableCell(withIdentifier:"ValidatorDetailHistoryEmpty") as? ValidatorDetailHistoryEmpty
                return cell!
            }
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if(indexPath.section == 1 && mHistories.count > 0) {
            let history = mHistories[indexPath.row]
            if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                guard let url = URL(string: "https://www.mintscan.io/txs/" + history._source.hash) else { return }
                let safariViewController = SFSafariViewController(url: url)
                present(safariViewController, animated: true, completion: nil)
                
            } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                guard let url = URL(string: "https://irishub.mintscan.io/txs/" + history._source.hash) else { return }
                let safariViewController = SFSafariViewController(url: url)
                present(safariViewController, animated: true, completion: nil)
                
            }
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if(indexPath.section == 0) {
            if (indexPath.row == 0 && mMyValidator) {
                return 225;
            } else if (indexPath.row == 0 && !mMyValidator) {
                return 285;
            } else {
                return 255;
            }
        } else {
            if(mHistories.count > 0) {
                return 80;
            } else {
                return 90;
            }
        }
        
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        if(section == 0) {
            return 0
        } else {
            return 30
        }
        
    }
    
    func onFetchValidatorInfo(_ validator: Validator) {
        var url = ""
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_VALIDATORS + "/" + validator.operator_address
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            url = IRIS_LCD_URL_VALIDATORS + "/" + validator.operator_address
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchValidatorInfo ", res)
                guard let validator = res as? NSDictionary else {
//                    print("no validator Error!!")
                    self.onFetchFinished()
                    return
                }
                self.mValidator = Validator(validator as! [String : Any])
                
            case .failure(let error):
                print("onFetchValidatorInfo ", error)
            }
//            print("onFetchValidatorInfo!!! ")
            self.onFetchFinished()
        }
    }
    
    func onFetchSignleBondingInfo(_ account: Account, _ validator: Validator) {
        var url = ""
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_BONDING + account.account_address + CSS_LCD_URL_BONDING_TAIL + "/" + validator.operator_address
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            url = IRIS_LCD_URL_BONDING + account.account_address + IRIS_LCD_URL_BONDING_TAIL + "/" + validator.operator_address
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                print("res : ", res)
                guard let rawData = res as? [String : Any], rawData["error"] == nil, rawData["shares"] != nil else {
                    self.onFetchFinished()
                    return
                }
                let bondinginfo = BondingInfo(rawData)
                if (self.userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    self.mBonding = Bonding(account.account_id, bondinginfo.validator_address, bondinginfo.shares, Date().millisecondsSince1970)
                    if(self.mBonding != nil && self.mBonding!.getBondingAmount(self.mValidator!) != NSDecimalNumber.zero) {
                        self.mFetchCnt = self.mFetchCnt + 1
                        self.onFetchRewardInfo(account, validator)
                    }
                } else if (self.userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    let shareAmount = WUtils.stringToDecimal(bondinginfo.shares).multiplying(byPowerOf10: 18)
                    self.mBonding = Bonding(account.account_id, bondinginfo.validator_addr, shareAmount.stringValue, Date().millisecondsSince1970)
                }
                
            case .failure(let error):
                print("onFetchSignleBondingInfo ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchSignleUnBondingInfo(_ account: Account, _ validator: Validator) {
        var url = ""
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_UNBONDING + account.account_address + CSS_LCD_URL_UNBONDING_TAIL + "/" + validator.operator_address
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            url = IRIS_LCD_URL_UNBONDING + account.account_address + IRIS_LCD_URL_UNBONDING_TAIL + "/" + validator.operator_address
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawData = res as? [String : Any], rawData["error"] == nil, rawData["code"] == nil else {
                    self.onFetchFinished()
                    return
                }
                let unbondinginfo = UnbondingInfo(rawData)
                if (self.userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                    for entry in unbondinginfo.entries {
                        self.mUnbondings.append(Unbonding(account.account_id, unbondinginfo.validator_address, entry.creation_height, WUtils.nodeTimeToInt64(input: entry.completion_time).millisecondsSince1970, entry.initial_balance, entry.balance, Date().millisecondsSince1970))
                    }

                } else if (self.userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                    let unbondingBalance = WUtils.stringToDecimal(unbondinginfo.balance.replacingOccurrences(of: "iris", with: "")).multiplying(byPowerOf10: 18, withBehavior: WUtils.handler0)
                    let initialBalance = WUtils.stringToDecimal(unbondinginfo.initial_balance.replacingOccurrences(of: "iris", with: "")).multiplying(byPowerOf10: 18, withBehavior: WUtils.handler0)
                    self.mUnbondings.append(Unbonding(account.account_id, unbondinginfo.validator_addr, unbondinginfo.creation_height, WUtils.nodeTimeToInt64(input: unbondinginfo.min_time).millisecondsSince1970, initialBalance.stringValue, unbondingBalance.stringValue, Date().millisecondsSince1970))
                }
                
            case .failure(let error):
                print("onFetchSignleUnBondingInfo ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchRewardInfo(_ account: Account, _ validator: Validator) {
        let url = CSS_LCD_URL_REWARD_FROM_VAL + account.account_address + CSS_LCD_URL_REWARD_FROM_VAL_TAIL + "/" + validator.operator_address
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let rawRewards = res as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return;
                }
                let reward = Reward.init()
                reward.reward_v_address = validator.operator_address
                for rawReward in rawRewards {
                    reward.reward_amount.append(Coin(rawReward as! [String : Any]))
                }
                self.mRewards.append(reward)
                
            case .failure(let error):
                print("onFetchEachReward ", error)
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchIrisReward(_ account: Account) {
        let url = IRIS_LCD_URL_REWARD + account.account_address + IRIS_LCD_URL_REWARD_TAIL
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let irisRewards = res as? NSDictionary else {
                    self.onFetchFinished()
                    return
                }
                self.mIrisRewards = IrisRewards(irisRewards as! [String : Any])
                
            case .failure(let error):
                print("onFetchIrisReward ", error)
            }
            self.onFetchFinished()
        }
        
    }
    
    func onFetchHistory(_ account: Account, _ validator: Validator, _ from:String, _ size:String) {
        var query = ""
        var url = ""
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            query = "{\"from\" : " + from + ",\"query\" : { \"bool\" : { \"must\" : [ { \"multi_match\" : { \"fields\" : [ \"tx.value.msg.value.delegator_addr\", \"tx.value.msg.value.delegator_address\" ], \"query\" : \"" + account.account_address + "\" } }, { \"multi_match\" : { \"fields\" : [ \"tx.value.msg.value.validator_address\", \"tx.value.msg.value.validator_dst_address\", \"tx.value.msg.value.validator_src_address\" ], \"query\" : \"" + validator.operator_address + "\"} } ] }  }, \"size\": " + size + ",\"sort\" : [ {  \"height\" : {  \"order\" : \"desc\" } } ] }"
            
            url = CSS_ES_PROXY_COSMOS
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            query = "{\"from\" : " + from + ",\"query\" : { \"bool\" : { \"must\" : [ { \"multi_match\" : { \"fields\" : [ \"tx.value.msg.value.delegator_addr\", \"tx.value.msg.value.delegator_address\" ], \"query\" : \"" + account.account_address + "\" } }, {  \"multi_match\" : { \"fields\" : [ \"tx.value.msg.value.validator_addr\", \"tx.value.msg.value.validator_address\", \"tx.value.msg.value.val_operator_addr\", \"tx.value.msg.value.validator_dst_addr\", \"tx.value.msg.value.validator_src_addr\", \"result.tags.key\" ], \"query\" : \"" + validator.operator_address + "\"  } } ]  } },  \"size\": " + size + ",\"sort\" : [ { \"height\" : {  \"order\" : \"desc\" } } ] }"
            url = IRIS_ES_PROXY_IRIS
        }
//        print("query ", query)
        let data = query.data(using: .utf8)
        do {
            let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
            let request = Alamofire.request(url, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
            request.responseJSON { response in
                switch response.result {
                case .success(let res):
                    guard let history = res as? [String : Any] else {
                        self.onFetchFinished()
                        return;
                    }
                    let rawHistory = History.init(history)
                    self.mHistories.removeAll()
                    self.mHistories = rawHistory.hits.hits
                case .failure(let error):
                    print("error ", error)
                }
                self.onFetchFinished()
            }
            
        } catch {
            print(error)
        }
        
    }
    
    func onFetchSelfBondRate(_ address: String, _ vAddress: String) {
        var url = ""
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_BONDING + address + CSS_LCD_URL_BONDING_TAIL + "/" + vAddress
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            url = IRIS_LCD_URL_BONDING + address + IRIS_LCD_URL_BONDING_TAIL + "/" + vAddress
        }
        print("onFetchSelfBondRate url ", url)
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchSelfBondRate ", res)
                guard let rawData = res as? [String : Any], rawData["error"] == nil else {
//                    print("no self bondinginfo Error!!")
                    self.onFetchFinished()
                    return
                }
                self.mSelfBondingShare = BondingInfo(rawData).shares
                
            case .failure(let error):
                print("onFetchSelfBondRate ", error)
            }
//            print("onFetchSelfBondRate!!! ")
            self.onFetchFinished()
        }
    }
    
    func onFetchRedelegatedState(_ address: String, _ to: String) {
        let request = Alamofire.request(CSS_LCD_URL_REDELEGATION, method: .get, parameters: ["delegator":address, "validator_to":to], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchRedelegatedState ", res)
                if let redelegateHistories = res as? Array<NSDictionary>, let entries = redelegateHistories[0]["entries"] as? Array<NSDictionary> {
                    if(entries.count >= 0) {
                        self.onShowToast(NSLocalizedString("error_redelegation_limitted", comment: ""))
                    } else {
                        self.onStartRedelegate()
                    }
                } else {
                    self.onStartRedelegate()
                    
                }
                
            case .failure(let error):
                print("onFetchRedelegatedState ", error)
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
    
    
    func onStartDelegate() {
        if (!mAccount!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        var balances = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if (balances.count <= 0 || WUtils.stringToDecimal(balances[0].balance_amount).compare(NSDecimalNumber.one).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (balances.count <= 0 || WUtils.stringToDecimal(balances[0].balance_amount).compare(NSDecimalNumber.init(string: "400000000000000000")).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        }
        
        let stakingVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "StakingViewController") as! StakingViewController
        stakingVC.mTargetValidator = mValidator
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            stakingVC.mType = COSMOS_MSG_TYPE_DELEGATE
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            stakingVC.mType = IRIS_MSG_TYPE_DELEGATE
        }
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(stakingVC, animated: true)
        
    }
    
    func onStartUndelegate() {
        if (!mAccount!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        if (mBonding == nil || self.mBonding!.getBondingAmount(mValidator!) == NSDecimalNumber.zero) {
            self.onShowToast(NSLocalizedString("error_not_undelegate", comment: ""))
            return
        }
        
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if (mUnbondings.count >= 7) {
                self.onShowToast(NSLocalizedString("error_unbonding_count_over", comment: ""))
                return
            }
            var balances = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
            if (balances.count <= 0 || WUtils.stringToDecimal(balances[0].balance_amount).compare(NSDecimalNumber.one).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (mUnbondings.count >= 1) {
                self.onShowToast(NSLocalizedString("error_unbonding_count_over", comment: ""))
                return
            }
            var balances = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
            if (balances.count <= 0 || WUtils.stringToDecimal(balances[0].balance_amount).compare(NSDecimalNumber(string: "400000000000000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        }
        
        let stakingVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "StakingViewController") as! StakingViewController
        stakingVC.mTargetValidator = mValidator
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            stakingVC.mType = COSMOS_MSG_TYPE_UNDELEGATE2
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            stakingVC.mType = IRIS_MSG_TYPE_UNDELEGATE
        }
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(stakingVC, animated: true)
        
    }
    
    
    func onCheckRedelegate() {
//        print("onStartRedelegate")
        if (!mAccount!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        if (mBonding == nil || self.mBonding!.getBondingAmount(mValidator!) == NSDecimalNumber.zero) {
            self.onShowToast(NSLocalizedString("error_not_redelegate", comment: ""))
            return
        }
        var balances = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
        if(balances.count <= 0 || WUtils.stringToDecimal(balances[0].balance_amount).compare(NSDecimalNumber(string: "1")).rawValue < 0) {
            self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
            return
        }
        
        self.onFetchRedelegatedState(mAccount!.account_address, mValidator!.operator_address)
    }
    
    func onStartRedelegate() {
        let stakingVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "StakingViewController") as! StakingViewController
        stakingVC.mTargetValidator = mValidator
        stakingVC.mInflation = mInflation
        stakingVC.mProvision = mProvision
        stakingVC.mStakingPool = mStakingPool
        stakingVC.mType = COSMOS_MSG_TYPE_REDELEGATE2
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(stakingVC, animated: true)
    }
    
    func onStartGetSingleReward() {
//        print("onStartGetSingleReward")
        if (!mAccount!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllAtomReward(mRewards)
                if(rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if(rewardSum.compare(NSDecimalNumber(string: "1")).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            
            var balances = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
            if(balances.count <= 0 || WUtils.stringToDecimal(balances[0].balance_amount).compare(NSDecimalNumber.one).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (mIrisRewards != nil) {
                let rewardSum = mIrisRewards?.getPerValReward(valOp: mValidator!.operator_address)
                if(rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if(rewardSum!.compare(NSDecimalNumber(string: "400000000000000000")).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            
            var balances = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
            if(balances.count <= 0 || WUtils.stringToDecimal(balances[0].balance_amount).compare(NSDecimalNumber(string: "400000000000000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        }
        
        let stakingVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "StakingViewController") as! StakingViewController
        var validators = Array<Validator>()
        validators.append(mValidator!)
        stakingVC.mRewardTargetValidators = validators
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            stakingVC.mType = COSMOS_MSG_TYPE_WITHDRAW_DEL
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            stakingVC.mType = IRIS_MSG_TYPE_WITHDRAW
        }
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(stakingVC, animated: true)
        
        
    }
    
    func onCheckReinvest() {
        print("onStartReinvest")
        if(!mAccount!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllAtomReward(mRewards)
                if(rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if(rewardSum.compare(NSDecimalNumber.one).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            
            var balances = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
            if (balances.count <= 0 || WUtils.stringToDecimal(balances[0].balance_amount).compare(NSDecimalNumber(string: "1")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            if (mIrisRewards != nil) {
                let rewardSum = mIrisRewards?.getPerValReward(valOp: mValidator!.operator_address)
                if(rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if(rewardSum!.compare(NSDecimalNumber(string: "400000000000000000")).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            
            var balances = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
            if(balances.count <= 0 || WUtils.stringToDecimal(balances[0].balance_amount).compare(NSDecimalNumber(string: "400000000000000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
        }
        self.onFetchRewardAddress(mAccount!.account_address)
    }
    
    func onStartReInvest() {
        let stakingVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "StakingViewController") as! StakingViewController
        stakingVC.mTargetValidator = self.mValidator
        stakingVC.mType = COSMOS_MULTI_MSG_TYPE_REINVEST
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(stakingVC, animated: true)
    }
    
    func onFetchRewardAddress(_ accountAddr: String) {
        var url = ""
        if (userChain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            url = CSS_LCD_URL_REWARD_ADDRESS + accountAddr + CSS_LCD_URL_REWARD_ADDRESS_TAIL
        } else if (userChain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            url = IRIS_LCD_URL_REWARD_ADDRESS + accountAddr + IRIS_LCD_URL_REWARD_ADDRESS_TAIL
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseString { (response) in
            switch response.result {
            case .success(let res):
                guard let address = res as? String else {
                    self.onShowReInvsetFailDialog()
                    return;
                }
                let trimAddress = address.replacingOccurrences(of: "\"", with: "")
                if(trimAddress == accountAddr) {
                    self.onStartReInvest()
                } else {
                    self.onShowReInvsetFailDialog()
                }
                
            case .failure(let error):
                if(SHOW_LOG) {
                    print("onFetchRewardAddress ", error)
                }
            }
        }
        
    }
    
    func onShowAddMenomicDialog() {
        let alert = UIAlertController(title: NSLocalizedString("alert_title_no_private_key", comment: ""), message: NSLocalizedString("alert_msg_no_private_key", comment: ""), preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: NSLocalizedString("add_mnemonic", comment: ""), style: .default, handler: { [weak alert] (_) in
            self.onStartImportMnemonic()
        }))
        alert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: ""), style: .cancel, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
    
    func onShowReInvsetFailDialog() {
        let alert = UIAlertController(title: NSLocalizedString("error_reward_address_changed_title", comment: ""), message: NSLocalizedString("error_reward_address_changed_msg", comment: ""), preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: NSLocalizedString("confirm", comment: ""), style: .cancel, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
}

