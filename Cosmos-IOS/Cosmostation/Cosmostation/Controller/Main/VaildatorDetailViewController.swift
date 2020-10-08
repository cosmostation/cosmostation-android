//
//  VaildatorDetailViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 04/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SafariServices

class VaildatorDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var chainBg: UIImageView!
    @IBOutlet weak var validatorDetailTableView: UITableView!
    @IBOutlet weak var loadingImg: LoadingImageView!
    
    var mValidator: Validator?
    var mBonding: Bonding?
    var mUnbondings = Array<Unbonding>()
    var mRewards = Array<Reward>()
    var mIrisRewards: IrisRewards?
    var mApiHistories = Array<ApiHistory.HistoryData>()
    var mSelfBondingShare: String?
    var mFetchCnt = 0
    var mMyValidator = false
    var mIsTop100 = false
    
    var mInflation: String?
    var mProvision: String?
    var mStakingPool: NSDictionary?
    var mIrisStakePool: NSDictionary?
    var mIrisRedelegate: Array<NSDictionary>?
    
    var refresher: UIRefreshControl!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.validatorDetailTableView.delegate = self
        self.validatorDetailTableView.dataSource = self
        self.validatorDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.validatorDetailTableView.register(UINib(nibName: "ValidatorDetailMyDetailCell", bundle: nil), forCellReuseIdentifier: "ValidatorDetailMyDetailCell")
        self.validatorDetailTableView.register(UINib(nibName: "ValidatorDetailMyActionCell", bundle: nil), forCellReuseIdentifier: "ValidatorDetailMyActionCell")
        self.validatorDetailTableView.register(UINib(nibName: "ValidatorDetailCell", bundle: nil), forCellReuseIdentifier: "ValidatorDetailCell")
        self.validatorDetailTableView.register(UINib(nibName: "ValidatorDetailHistoryEmpty", bundle: nil), forCellReuseIdentifier: "ValidatorDetailHistoryEmpty")
        self.validatorDetailTableView.register(UINib(nibName: "HistoryCell", bundle: nil), forCellReuseIdentifier: "HistoryCell")
        self.validatorDetailTableView.rowHeight = UITableView.automaticDimension
        self.validatorDetailTableView.estimatedRowHeight = UITableView.automaticDimension
        
        refresher = UIRefreshControl()
        refresher.addTarget(self, action: #selector(onFech), for: .valueChanged)
        refresher.tintColor = UIColor.white
        validatorDetailTableView.addSubview(refresher)
        
        self.mInflation = BaseData.instance.mInflation
        self.mProvision = BaseData.instance.mProvision
        self.mStakingPool = BaseData.instance.mStakingPool
        self.mIrisStakePool = BaseData.instance.mIrisStakePool
        
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
    
    @objc func onFech() {
        if (chainType == ChainType.COSMOS_MAIN) {
            mUnbondings.removeAll()
            mRewards.removeAll()
            mFetchCnt = 5
            onFetchValidatorInfo(mValidator!)
            onFetchSignleBondingInfo(account!, mValidator!)
            onFetchSignleUnBondingInfo(account!, mValidator!)
            onFetchSelfBondRate(WKey.getAddressFromOpAddress(mValidator!.operator_address, chainType!), mValidator!.operator_address)
            onFetchApiHistory(account!, mValidator!)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            mUnbondings.removeAll()
            mFetchCnt = 6
            onFetchValidatorInfo(mValidator!)
            onFetchSignleBondingInfo(account!, mValidator!)
            onFetchSignleUnBondingInfo(account!, mValidator!)
            onFetchSelfBondRate(WKey.getAddressFromOpAddress(mValidator!.operator_address, chainType!), mValidator!.operator_address)
            onFetchIrisReward(account!)
            onFetchApiHistory(account!, mValidator!)
            
        } else if (chainType == ChainType.KAVA_MAIN) {
            mUnbondings.removeAll()
            mRewards.removeAll()
            mFetchCnt = 5
            onFetchValidatorInfo(mValidator!)
            onFetchSignleBondingInfo(account!, mValidator!)
            onFetchSignleUnBondingInfo(account!, mValidator!)
            onFetchSelfBondRate(WKey.getAddressFromOpAddress(mValidator!.operator_address, chainType!), mValidator!.operator_address)
            onFetchApiHistory(account!, mValidator!)
            
        } else if (chainType == ChainType.KAVA_TEST) {
            mUnbondings.removeAll()
            mRewards.removeAll()
            mFetchCnt = 5
            onFetchValidatorInfo(mValidator!)
            onFetchSignleBondingInfo(account!, mValidator!)
            onFetchSignleUnBondingInfo(account!, mValidator!)
            onFetchSelfBondRate(WKey.getAddressFromOpAddress(mValidator!.operator_address, chainType!), mValidator!.operator_address)
            onFetchApiHistory(account!, mValidator!)
            
        } else if (chainType == ChainType.BAND_MAIN) {
            mUnbondings.removeAll()
            mRewards.removeAll()
            mFetchCnt = 5
            onFetchValidatorInfo(mValidator!)
            onFetchSignleBondingInfo(account!, mValidator!)
            onFetchSignleUnBondingInfo(account!, mValidator!)
            onFetchSelfBondRate(WKey.getAddressFromOpAddress(mValidator!.operator_address, chainType!), mValidator!.operator_address)
            onFetchApiHistory(account!, mValidator!)
            
        } else if (chainType == ChainType.IOV_MAIN) {
            mUnbondings.removeAll()
            mRewards.removeAll()
            mFetchCnt = 5
            onFetchValidatorInfo(mValidator!)
            onFetchSignleBondingInfo(account!, mValidator!)
            onFetchSignleUnBondingInfo(account!, mValidator!)
            onFetchSelfBondRate(WKey.getAddressFromOpAddress(mValidator!.operator_address, chainType!), mValidator!.operator_address)
            onFetchApiHistory(account!, mValidator!)
            
        } else if (chainType == ChainType.IOV_TEST ) {
            mUnbondings.removeAll()
            mRewards.removeAll()
            mFetchCnt = 4
            onFetchValidatorInfo(mValidator!)
            onFetchSignleBondingInfo(account!, mValidator!)
            onFetchSignleUnBondingInfo(account!, mValidator!)
            onFetchSelfBondRate(WKey.getAddressFromOpAddress(mValidator!.operator_address, chainType!), mValidator!.operator_address)
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            mUnbondings.removeAll()
            mRewards.removeAll()
            mFetchCnt = 5
            onFetchValidatorInfo(mValidator!)
            onFetchSignleBondingInfo(account!, mValidator!)
            onFetchSignleUnBondingInfo(account!, mValidator!)
            onFetchSelfBondRate(WKey.getAddressFromOpAddress(mValidator!.operator_address, chainType!), mValidator!.operator_address)
            onFetchApiHistory(account!, mValidator!)
        }
        
    }
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
//        print("onFetchFinished ", self.mFetchCnt)
        if(mFetchCnt <= 0) {
//            print("onFetchFinished mBonding ", mBonding)
//            print("onFetchFinished mBonding Amount ", mBonding?.getBondingAmount(mValidator!))
//            print("onFetchFinished mBonding Amount stringValue", mBonding?.getBondingAmount(mValidator!).stringValue)
//            print("onFetchFinished mBonding Share ", mBonding?.bonding_shares)
//            print("onFetchFinished mUnbondings ", mUnbondings.count)
//            print("onFetchFinished mRewards ", mRewards.count)

            if((mBonding != nil && NSDecimalNumber.init(string: mBonding?.bonding_shares) != NSDecimalNumber.zero) || mUnbondings.count > 0) {
                mMyValidator = true
            } else {
                mMyValidator = false
            }
//            print("mMyValidator ", mMyValidator)
            
            self.validatorDetailTableView.reloadData()
            
            self.loadingImg.onStopAnimation()
            self.loadingImg.isHidden = true
            self.validatorDetailTableView.isHidden = false
            self.refresher.endRefreshing()
            
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            if (mMyValidator) {
                return 2
            } else {
                return 1
            }
            
        } else {
            if (mApiHistories.count > 0) {
                return mApiHistories.count
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
                return onSetMyValidatorItems(tableView, indexPath)
            } else if (indexPath.row == 0 && !mMyValidator) {
                return onSetValidatorItems(tableView, indexPath)
            } else {
                return onSetActionItems(tableView, indexPath)
            }
        } else {
            return onSetHistoryItems(tableView, indexPath)
        }
    }
    
    
    func onSetMyValidatorItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:ValidatorDetailMyDetailCell? = tableView.dequeueReusableCell(withIdentifier:"ValidatorDetailMyDetailCell") as? ValidatorDetailMyDetailCell
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
        cell?.actionTapUrl = {
            guard let url = URL(string: self.mValidator!.description.website) else { return }
            if (UIApplication.shared.canOpenURL(url)) {
                let safariViewController = SFSafariViewController(url: url)
                safariViewController.modalPresentationStyle = .popover
                self.present(safariViewController, animated: true, completion: nil)
            }
        }
        if (chainType == ChainType.COSMOS_MAIN) {
            cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.commission_rates.rate, font: cell!.commissionRate.font)
            cell?.totalBondedAmount.attributedText =  WUtils.displayAmount2(mValidator!.tokens, cell!.totalBondedAmount.font!, 6, 6)
            let url = COSMOS_VAL_URL + mValidator!.operator_address + ".png"
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.rate, font: cell!.commissionRate.font)
            cell?.totalBondedAmount.attributedText =  WUtils.displayAmount2(mValidator!.tokens, cell!.totalBondedAmount.font!, 0, 18)
            let url = IRIS_VAL_URL + mValidator!.operator_address + ".png"
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.commission_rates.rate, font: cell!.commissionRate.font)
            cell?.totalBondedAmount.attributedText =  WUtils.displayAmount2(mValidator!.tokens, cell!.totalBondedAmount.font!, 6, 6)
            let url = KAVA_VAL_URL + mValidator!.operator_address + ".png"
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.BAND_MAIN) {
            cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.commission_rates.rate, font: cell!.commissionRate.font)
            cell?.totalBondedAmount.attributedText =  WUtils.displayAmount2(mValidator!.tokens, cell!.totalBondedAmount.font!, 6, 6)
            let url = BAND_VAL_URL + mValidator!.operator_address + ".png"
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
            cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.commission_rates.rate, font: cell!.commissionRate.font)
            cell?.totalBondedAmount.attributedText =  WUtils.displayAmount2(mValidator!.tokens, cell!.totalBondedAmount.font!, 6, 6)
            let url = IOV_VAL_URL + mValidator!.operator_address + ".png"
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.commission_rates.rate, font: cell!.commissionRate.font)
            cell?.totalBondedAmount.attributedText =  WUtils.displayAmount2(mValidator!.tokens, cell!.totalBondedAmount.font!, 6, 6)
            let url = CERTIK_VAL_URL + mValidator!.operator_address + ".png"
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
        }
        
        if (mSelfBondingShare != nil) {
            cell!.selfBondedRate.attributedText = WUtils.displaySelfBondRate(mSelfBondingShare!, mValidator!.tokens, cell!.selfBondedRate.font)
        } else {
            cell!.selfBondedRate.attributedText = WUtils.displaySelfBondRate(NSDecimalNumber.zero.stringValue, mValidator!.tokens, cell!.selfBondedRate.font)
        }
        
        if (mIsTop100 && chainType == ChainType.COSMOS_MAIN) {
            if (mStakingPool != nil && mProvision != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), font: cell!.avergaeYield.font)
            } else {
                cell!.avergaeYield.text = "?? %"
            }
            
        } else if (mIsTop100 && chainType == ChainType.IRIS_MAIN) {
            if (mIrisStakePool != nil) {
                let provisions = NSDecimalNumber.init(string: mIrisStakePool?.object(forKey: "total_supply") as? String).multiplying(by: NSDecimalNumber.init(string: "0.04"))
                let bonded_tokens = NSDecimalNumber.init(string: mIrisStakePool?.object(forKey: "bonded_tokens") as? String)
                cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.rate), font: cell!.avergaeYield.font)
            } else {
                cell!.avergaeYield.text = "?? %"
            }
            
        } else if (mIsTop100 && (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST)) {
            if (mStakingPool != nil && mProvision != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), font: cell!.avergaeYield.font)
            } else {
                cell!.avergaeYield.text = "?? %"
            }
            
        } else if (mIsTop100 && chainType == ChainType.BAND_MAIN) {
            if (mStakingPool != nil && mProvision != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), font: cell!.avergaeYield.font)
            } else {
                cell!.avergaeYield.text = "?? %"
            }
            
        } else if (mIsTop100 && (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST)) {
            if (mStakingPool != nil && mProvision != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), font: cell!.avergaeYield.font)
            } else {
                cell!.avergaeYield.text = "?? %"
            }
            
        } else if (mIsTop100 && chainType == ChainType.CERTIK_TEST) {
            if (mStakingPool != nil && mProvision != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), font: cell!.avergaeYield.font)
            } else {
                cell!.avergaeYield.text = "?? %"
            }
        } else {
            cell!.avergaeYield.attributedText = WUtils.displayCommission(NSDecimalNumber.zero.stringValue, font: cell!.avergaeYield.font)
            cell!.avergaeYield.textColor = UIColor.init(hexString: "f31963")
        }
        return cell!
    }
    
    func onSetValidatorItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
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
        cell?.actionTapUrl = {
            guard let url = URL(string: self.mValidator!.description.website) else { return }
            if (UIApplication.shared.canOpenURL(url)) {
                let safariViewController = SFSafariViewController(url: url)
                safariViewController.modalPresentationStyle = .popover
                self.present(safariViewController, animated: true, completion: nil)
            }
        }
        
        if (chainType == ChainType.COSMOS_MAIN) {
            cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.commission_rates.rate, font: cell!.commissionRate.font)
            cell?.totalBondedAmount.attributedText =  WUtils.displayAmount2(mValidator!.tokens, cell!.totalBondedAmount.font!, 6, 6)
            let url = COSMOS_VAL_URL + mValidator!.operator_address + ".png"
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.rate, font: cell!.commissionRate.font)
            cell?.totalBondedAmount.attributedText =  WUtils.displayAmount2(mValidator!.tokens, cell!.totalBondedAmount.font!, 0, 18)
            let url = IRIS_VAL_URL + mValidator!.operator_address + ".png"
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.commission_rates.rate, font: cell!.commissionRate.font)
            cell?.totalBondedAmount.attributedText =  WUtils.displayAmount2(mValidator!.tokens, cell!.totalBondedAmount.font!, 6, 6)
            let url = KAVA_VAL_URL + mValidator!.operator_address + ".png"
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.BAND_MAIN) {
            cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.commission_rates.rate, font: cell!.commissionRate.font)
            cell?.totalBondedAmount.attributedText =  WUtils.displayAmount2(mValidator!.tokens, cell!.totalBondedAmount.font!, 6, 6)
            let url = BAND_VAL_URL + mValidator!.operator_address + ".png"
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
            cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.commission_rates.rate, font: cell!.commissionRate.font)
            cell?.totalBondedAmount.attributedText =  WUtils.displayAmount2(mValidator!.tokens, cell!.totalBondedAmount.font!, 6, 6)
            let url = IOV_VAL_URL + mValidator!.operator_address + ".png"
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            cell!.commissionRate.attributedText = WUtils.displayCommission(mValidator!.commission.commission_rates.rate, font: cell!.commissionRate.font)
            cell?.totalBondedAmount.attributedText =  WUtils.displayAmount2(mValidator!.tokens, cell!.totalBondedAmount.font!, 6, 6)
            let url = CERTIK_VAL_URL + mValidator!.operator_address + ".png"
            cell?.validatorImg.af_setImage(withURL: URL(string: url)!)
        }
        
        if (mSelfBondingShare != nil) {
            cell!.selfBondedRate.attributedText = WUtils.displaySelfBondRate(mSelfBondingShare!, mValidator!.tokens, cell!.selfBondedRate.font)
        } else {
            cell!.selfBondedRate.attributedText = WUtils.displaySelfBondRate(NSDecimalNumber.zero.stringValue, mValidator!.tokens, cell!.selfBondedRate.font)
        }
        
        if (mIsTop100 && chainType == ChainType.COSMOS_MAIN) {
            if (mStakingPool != nil && mProvision != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), font: cell!.avergaeYield.font)

            } else {
                cell!.avergaeYield.text = "?? %"
            }
            
        } else if (mIsTop100 && chainType == ChainType.IRIS_MAIN) {
            if (mIrisStakePool != nil) {
                let provisions = NSDecimalNumber.init(string: mIrisStakePool?.object(forKey: "total_supply") as? String).multiplying(by: NSDecimalNumber.init(string: "0.04"))
                let bonded_tokens = NSDecimalNumber.init(string: mIrisStakePool?.object(forKey: "bonded_tokens") as? String)
                cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.rate), font: cell!.avergaeYield.font)
            } else {
                cell!.avergaeYield.text = "?? %"
            }
            
        } else if (mIsTop100 && (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST)) {
            if (mStakingPool != nil && mProvision != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), font: cell!.avergaeYield.font)
            } else {
                cell!.avergaeYield.text = "?? %"
            }
            
        } else if (mIsTop100 && chainType == ChainType.BAND_MAIN) {
            if (mStakingPool != nil && mProvision != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), font: cell!.avergaeYield.font)

            } else {
                cell!.avergaeYield.text = "?? %"
            }
            
        } else if (mIsTop100 && (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST)) {
            if (mStakingPool != nil && mProvision != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), font: cell!.avergaeYield.font)

            } else {
                cell!.avergaeYield.text = "?? %"
            }
            
        } else if (mIsTop100 && chainType == ChainType.CERTIK_TEST) {
            if (mStakingPool != nil && mProvision != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.avergaeYield.attributedText = WUtils.displayYield(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), font: cell!.avergaeYield.font)

            } else {
                cell!.avergaeYield.text = "?? %"
            }
            
        } else {
            cell!.avergaeYield.attributedText = WUtils.displayCommission(NSDecimalNumber.zero.stringValue, font: cell!.avergaeYield.font)
            cell!.avergaeYield.textColor = UIColor.init(hexString: "f31963")
        }
        
        cell?.actionDelegate = {
            if (self.mValidator!.jailed) {
                self.onShowToast(NSLocalizedString("error_jailded_delegate", comment: ""))
                return
            } else {
                self.onStartDelegate()
            }
        }
        return cell!
        
    }
    
    func onSetActionItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:ValidatorDetailMyActionCell? = tableView.dequeueReusableCell(withIdentifier:"ValidatorDetailMyActionCell") as? ValidatorDetailMyActionCell
        cell?.cardView.backgroundColor = WUtils.getChainBg(chainType!)
        
        if (chainType == ChainType.COSMOS_MAIN) {
            if (mBonding != nil) {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount((mBonding?.getBondingAmount(mValidator!).stringValue)!, cell!.myDelegateAmount.font, 6, chainType!)
            } else {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myDelegateAmount.font, 6, chainType!)
            }
            if (mUnbondings.count > 0) {
                var unbondSum = NSDecimalNumber.zero
                for unbonding in mUnbondings {
                    unbondSum  = unbondSum.adding(WUtils.localeStringToDecimal(unbonding.unbonding_balance))
                }
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount(unbondSum.stringValue, cell!.myUndelegateAmount.font, 6, chainType!)
            } else {
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myUndelegateAmount.font, 6, chainType!)
            }
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, COSMOS_MAIN_DENOM)
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount(rewardSum.stringValue, cell!.myRewardAmount.font, 6, chainType!)
            } else {
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myRewardAmount.font, 6, chainType!)
            }
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            if (mBonding != nil) {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount2((mBonding?.getBondingAmount(mValidator!).stringValue)!, cell!.myDelegateAmount.font, 18, 18)
            } else {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.myDelegateAmount.font, 18, 18)
            }
            if (mUnbondings.count > 0) {
                var unbondSum = NSDecimalNumber.zero
                for unbonding in mUnbondings {
                    unbondSum  = unbondSum.adding(WUtils.localeStringToDecimal(unbonding.unbonding_balance))
                }
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount2(unbondSum.stringValue, cell!.myUndelegateAmount.font, 18, 18)
            } else {
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.myUndelegateAmount.font, 18, 18)
            }
            if (mIrisRewards != nil) {
                cell!.myRewardAmount.attributedText = WUtils.displayAmount2((mIrisRewards?.getPerValReward(valOp: mValidator!.operator_address).stringValue)!, cell!.myRewardAmount.font, 18, 18)
            } else {
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.myRewardAmount.font, 18, 18)
            }
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if(mBonding != nil) {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount((mBonding?.getBondingAmount(mValidator!).stringValue)!, cell!.myDelegateAmount.font, 6, chainType!)
            } else {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myDelegateAmount.font, 6, chainType!)
            }
            if (mUnbondings.count > 0) {
                var unbondSum = NSDecimalNumber.zero
                for unbonding in mUnbondings {
                    unbondSum  = unbondSum.adding(WUtils.localeStringToDecimal(unbonding.unbonding_balance))
                }
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount(unbondSum.stringValue, cell!.myUndelegateAmount.font, 6, chainType!)
            } else {
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myUndelegateAmount.font, 6, chainType!)
            }
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, KAVA_MAIN_DENOM)
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount(rewardSum.stringValue, cell!.myRewardAmount.font, 6, chainType!)
            } else {
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myRewardAmount.font, 6, chainType!)
            }
            
        } else if (chainType == ChainType.BAND_MAIN) {
            if (mBonding != nil) {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount((mBonding?.getBondingAmount(mValidator!).stringValue)!, cell!.myDelegateAmount.font, 6, chainType!)
            } else {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myDelegateAmount.font, 6, chainType!)
            }
            if (mUnbondings.count > 0) {
                var unbondSum = NSDecimalNumber.zero
                for unbonding in mUnbondings {
                    unbondSum  = unbondSum.adding(WUtils.localeStringToDecimal(unbonding.unbonding_balance))
                }
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount(unbondSum.stringValue, cell!.myUndelegateAmount.font, 6, chainType!)
            } else {
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myUndelegateAmount.font, 6, chainType!)
            }
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, BAND_MAIN_DENOM)
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount(rewardSum.stringValue, cell!.myRewardAmount.font, 6, chainType!)
            } else {
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount(NSDecimalNumber.zero.stringValue, cell!.myRewardAmount.font, 6, chainType!)
            }
            
        } else if (chainType == ChainType.IOV_MAIN) {
            if (mBonding != nil) {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount2((mBonding?.getBondingAmount(mValidator!).stringValue)!, cell!.myDelegateAmount.font, 6, 6)
            } else {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.myDelegateAmount.font, 6, 6)
            }
            if (mUnbondings.count > 0) {
                var unbondSum = NSDecimalNumber.zero
                for unbonding in mUnbondings {
                    unbondSum  = unbondSum.adding(WUtils.localeStringToDecimal(unbonding.unbonding_balance))
                }
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount2(unbondSum.stringValue, cell!.myUndelegateAmount.font, 6, 6)
            } else {
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.myUndelegateAmount.font, 6, 6)
            }
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, IOV_MAIN_DENOM)
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount2(rewardSum.stringValue, cell!.myRewardAmount.font, 6, 6)
            } else {
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.myRewardAmount.font, 6, 6)
            }
            
        } else if (chainType == ChainType.IOV_TEST) {
            if (mBonding != nil) {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount2((mBonding?.getBondingAmount(mValidator!).stringValue)!, cell!.myDelegateAmount.font, 6, 6)
            } else {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.myDelegateAmount.font, 6, 6)
            }
            if (mUnbondings.count > 0) {
                var unbondSum = NSDecimalNumber.zero
                for unbonding in mUnbondings {
                    unbondSum  = unbondSum.adding(WUtils.localeStringToDecimal(unbonding.unbonding_balance))
                }
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount2(unbondSum.stringValue, cell!.myUndelegateAmount.font, 6, 6)
            } else {
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.myUndelegateAmount.font, 6, 6)
            }
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, IOV_TEST_DENOM)
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount2(rewardSum.stringValue, cell!.myRewardAmount.font, 6, 6)
            } else {
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.myRewardAmount.font, 6, 6)
            }
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            if (mBonding != nil) {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount2((mBonding?.getBondingAmount(mValidator!).stringValue)!, cell!.myDelegateAmount.font, 6, 6)
            } else {
                cell!.myDelegateAmount.attributedText =  WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.myDelegateAmount.font, 6, 6)
            }
            if (mUnbondings.count > 0) {
                var unbondSum = NSDecimalNumber.zero
                for unbonding in mUnbondings {
                    unbondSum  = unbondSum.adding(WUtils.localeStringToDecimal(unbonding.unbonding_balance))
                }
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount2(unbondSum.stringValue, cell!.myUndelegateAmount.font, 6, 6)
            } else {
                cell!.myUndelegateAmount.attributedText =  WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.myUndelegateAmount.font, 6, 6)
            }
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, CERTIK_TEST_DENOM)
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount2(rewardSum.stringValue, cell!.myRewardAmount.font, 6, 6)
            } else {
                cell!.myRewardAmount.attributedText =  WUtils.displayAmount2(NSDecimalNumber.zero.stringValue, cell!.myRewardAmount.font, 6, 6)
            }
        }
        
        if (mIsTop100 && chainType == ChainType.COSMOS_MAIN) {
            if (mStakingPool != nil && mProvision != nil && mBonding != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.myDailyReturns.attributedText = WUtils.displayDailyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myDailyReturns.font, baseChain: chainType!)
                cell!.myMonthlyReturns.attributedText = WUtils.displayMonthlyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myMonthlyReturns.font, baseChain: chainType!)
            } else {
                cell!.myDailyReturns.text = "-"
                cell!.myMonthlyReturns.text = "-"
            }
            
        } else if (mIsTop100 && chainType == ChainType.IRIS_MAIN) {
            if (mIrisStakePool != nil && mBonding != nil) {
                let provisions = NSDecimalNumber.init(string: mIrisStakePool?.object(forKey: "total_supply") as? String).multiplying(by: NSDecimalNumber.init(string: "0.04"), withBehavior: WUtils.handler0Down)
                let bonded_tokens = NSDecimalNumber.init(string: mIrisStakePool?.object(forKey: "bonded_tokens") as? String)
//                print("provisions ", provisions)
//                print("bonded_tokens ", bonded_tokens)
//                print("(mBonding?.getBondingAmount(mValidator!))! ", (mBonding?.getBondingAmount(mValidator!))!)
//                print("NSDecimalNumber.init(string: mValidator!.commission.rate)! ", NSDecimalNumber.init(string: mValidator!.commission.rate))
                cell!.myDailyReturns.attributedText = WUtils.displayDailyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myDailyReturns.font, baseChain: chainType!)
                cell!.myMonthlyReturns.attributedText = WUtils.displayMonthlyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myMonthlyReturns.font, baseChain: chainType!)
            } else {
                cell!.myDailyReturns.text = "-"
                cell!.myMonthlyReturns.text = "-"
            }
            
        } else if (mIsTop100 && (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST)) {
            if (mStakingPool != nil && mProvision != nil && mBonding != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.myDailyReturns.attributedText = WUtils.displayDailyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myDailyReturns.font, baseChain: chainType!)
                cell!.myMonthlyReturns.attributedText = WUtils.displayMonthlyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myMonthlyReturns.font, baseChain: chainType!)
                
            } else {
                cell!.myDailyReturns.text = "-"
                cell!.myMonthlyReturns.text = "-"
            }
            
        } else if (mIsTop100 && chainType == ChainType.BAND_MAIN) {
            if (mStakingPool != nil && mProvision != nil && mBonding != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.myDailyReturns.attributedText = WUtils.displayDailyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myDailyReturns.font, baseChain: chainType!)
                cell!.myMonthlyReturns.attributedText = WUtils.displayMonthlyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myMonthlyReturns.font, baseChain: chainType!)
            } else {
                cell!.myDailyReturns.text = "-"
                cell!.myMonthlyReturns.text = "-"
            }
            
        } else if (mIsTop100 && (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST)) {
            if (mStakingPool != nil && mProvision != nil && mBonding != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.myDailyReturns.attributedText = WUtils.displayDailyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myDailyReturns.font, baseChain: chainType!)
                cell!.myMonthlyReturns.attributedText = WUtils.displayMonthlyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myMonthlyReturns.font, baseChain: chainType!)
            } else {
                cell!.myDailyReturns.text = "-"
                cell!.myMonthlyReturns.text = "-"
            }
            
        } else if (mIsTop100 && chainType == ChainType.CERTIK_TEST) {
            if (mStakingPool != nil && mProvision != nil && mBonding != nil) {
                let provisions = NSDecimalNumber.init(string: mProvision)
                let bonded_tokens = NSDecimalNumber.init(string: mStakingPool?.object(forKey: "bonded_tokens") as? String)
                cell!.myDailyReturns.attributedText = WUtils.displayDailyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myDailyReturns.font, baseChain: chainType!)
                cell!.myMonthlyReturns.attributedText = WUtils.displayMonthlyReturns(bonded_tokens, provisions, NSDecimalNumber.init(string: mValidator!.commission.commission_rates.rate), (mBonding?.getBondingAmount(mValidator!))! , font: cell!.myMonthlyReturns.font, baseChain: chainType!)
            } else {
                cell!.myDailyReturns.text = "-"
                cell!.myMonthlyReturns.text = "-"
            }
            
        } else {
            cell!.myDailyReturns.attributedText = WUtils.displayDailyReturns(NSDecimalNumber.one, NSDecimalNumber.one, NSDecimalNumber.one, NSDecimalNumber.one, font: cell!.myDailyReturns.font, baseChain: chainType!)
            cell!.myMonthlyReturns.attributedText = WUtils.displayMonthlyReturns(NSDecimalNumber.one, NSDecimalNumber.one, NSDecimalNumber.one, NSDecimalNumber.one, font: cell!.myMonthlyReturns.font, baseChain: chainType!)
            cell!.myDailyReturns.textColor = UIColor.init(hexString: "f31963")
            cell!.myMonthlyReturns.textColor = UIColor.init(hexString: "f31963")
        }
        
        cell?.actionDelegate = {
            if (self.mValidator!.jailed) {
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
    
    func onSetHistoryItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        if (mApiHistories.count > 0) {
            let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
            let history = mApiHistories[indexPath.row]
            if (chainType == ChainType.IRIS_MAIN) {
                cell?.txBlockLabel.text = String(history.height) + " block"
                cell?.txTypeLabel.text = WUtils.historyTitle(history.msg, account!.account_address)
                cell?.txTimeLabel.text = WUtils.txTimetoString(input: history.time)
                cell?.txTimeGapLabel.text = WUtils.txTimeGap(input: history.time)
                if (history.result.code > 0) {
                    cell?.txResultLabel.isHidden = false
                } else {
                    cell?.txResultLabel.isHidden = true
                }
                
            } else {
                cell?.txBlockLabel.text = String(history.height) + " block"
                cell?.txTypeLabel.text = WUtils.historyTitle(history.msg, account!.account_address)
                cell?.txTimeLabel.text = WUtils.txTimetoString(input: history.time)
                cell?.txTimeGapLabel.text = WUtils.txTimeGap(input: history.time)
                if(history.isSuccess) {
                    cell?.txResultLabel.isHidden = true
                } else {
                    cell?.txResultLabel.isHidden = false
                }
            }
            return cell!
        } else {
            let cell:ValidatorDetailHistoryEmpty? = tableView.dequeueReusableCell(withIdentifier:"ValidatorDetailHistoryEmpty") as? ValidatorDetailHistoryEmpty
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.section == 1 && mApiHistories.count > 0) {
            let history = mApiHistories[indexPath.row]
            let txDetailVC = TxDetailViewController(nibName: "TxDetailViewController", bundle: nil)
            txDetailVC.mIsGen = false
            txDetailVC.mTxHash = history.tx_hash
            txDetailVC.hidesBottomBarWhenPushed = true
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(txDetailVC, animated: true)
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension;
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        if (section == 0) {
            return 0
        } else {
            return 30
        }
    }
    
    func onFetchValidatorInfo(_ validator: Validator) {
        var url: String?
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_VALIDATORS + "/" + validator.operator_address
        } else if (chainType == ChainType.IRIS_MAIN) {
            url = IRIS_LCD_URL_VALIDATORS + "/" + validator.operator_address
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_VALIDATORS + "/" + validator.operator_address
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_VALIDATORS + "/" + validator.operator_address
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_VALIDATORS + "/" + validator.operator_address
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_VALIDATORS + "/" + validator.operator_address
        } else if (chainType == ChainType.IOV_TEST) {
            url = IOV_TEST_VALIDATORS + "/" + validator.operator_address
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_VALIDATORS + "/" + validator.operator_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.KAVA_MAIN || self.chainType == ChainType.KAVA_TEST ||
                    self.chainType == ChainType.BAND_MAIN || self.chainType == ChainType.IOV_MAIN || self.chainType == ChainType.IOV_TEST || self.chainType == ChainType.CERTIK_TEST) {
                    guard let responseData = res as? NSDictionary,
                        let validator = responseData.object(forKey: "result") as? NSDictionary else {
                        self.onFetchFinished()
                        return
                    }
                    self.mValidator = Validator(validator as! [String : Any])
                    
                } else if (self.chainType == ChainType.IRIS_MAIN) {
                    guard let validator = res as? NSDictionary else {
                        self.onFetchFinished()
                        return
                    }
                    self.mValidator = Validator(validator as! [String : Any])
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchValidatorInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchSignleBondingInfo(_ account: Account, _ validator: Validator) {
        var url: String?
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_BONDING + account.account_address + COSMOS_URL_BONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.IRIS_MAIN) {
            url = IRIS_LCD_URL_BONDING + account.account_address + IRIS_LCD_URL_BONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_BONDING + account.account_address + KAVA_BONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_BONDING + account.account_address + KAVA_TEST_BONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_BONDING + account.account_address + BAND_BONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_BONDING + account.account_address + IOV_BONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.IOV_TEST) {
            url = IOV_TEST_BONDING + account.account_address + IOV_TEST_BONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_BONDING + account.account_address + CERTIK_TEST_BONDING_TAIL + "/" + validator.operator_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.KAVA_MAIN || self.chainType == ChainType.KAVA_TEST ||
                    self.chainType == ChainType.BAND_MAIN || self.chainType == ChainType.IOV_MAIN || self.chainType == ChainType.IOV_TEST || self.chainType == ChainType.CERTIK_TEST) {
                    guard let responseData = res as? NSDictionary,
                        let rawData = responseData.object(forKey: "result") as? [String : Any] else {
                        self.onFetchFinished()
                        return
                    }
                    let bondinginfo = BondingInfo(rawData)
                    self.mBonding = Bonding(account.account_id, bondinginfo.validator_address, bondinginfo.shares, Date().millisecondsSince1970)
                    if(self.mBonding != nil && self.mBonding!.getBondingAmount(self.mValidator!) != NSDecimalNumber.zero) {
                        self.mFetchCnt = self.mFetchCnt + 1
                        self.onFetchRewardInfo(account, validator)
                    }
                    
                } else if (self.chainType == ChainType.IRIS_MAIN) {
                    guard let rawData = res as? [String : Any], rawData["error"] == nil, rawData["shares"] != nil else {
                        self.onFetchFinished()
                        return
                    }
                    let bondinginfo = BondingInfo(rawData)
                    let shareAmount = WUtils.plainStringToDecimal(bondinginfo.shares).multiplying(byPowerOf10: 18)
                    self.mBonding = Bonding(account.account_id, bondinginfo.validator_addr, shareAmount.stringValue, Date().millisecondsSince1970)
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchSignleBondingInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchSignleUnBondingInfo(_ account: Account, _ validator: Validator) {
        var url: String?
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_UNBONDING + account.account_address + COSMOS_URL_UNBONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.IRIS_MAIN) {
            url = IRIS_LCD_URL_UNBONDING + account.account_address + IRIS_LCD_URL_UNBONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_UNBONDING + account.account_address + KAVA_UNBONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_UNBONDING + account.account_address + KAVA_TEST_UNBONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_UNBONDING + account.account_address + BAND_UNBONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_UNBONDING + account.account_address + IOV_UNBONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.IOV_TEST) {
            url = IOV_TEST_UNBONDING + account.account_address + IOV_TEST_UNBONDING_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_UNBONDING + account.account_address + CERTIK_TEST_UNBONDING_TAIL + "/" + validator.operator_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.KAVA_MAIN || self.chainType == ChainType.KAVA_TEST ||
                    self.chainType == ChainType.BAND_MAIN || self.chainType == ChainType.IOV_MAIN || self.chainType == ChainType.IOV_TEST || self.chainType == ChainType.CERTIK_TEST) {
                    guard let responseData = res as? NSDictionary,
                        let rawData = responseData.object(forKey: "result") as? [String : Any] else {
                        self.onFetchFinished()
                        return
                    }
                    let unbondinginfo = UnbondingInfo(rawData)
                    for entry in unbondinginfo.entries {
                        self.mUnbondings.append(Unbonding(account.account_id, unbondinginfo.validator_address, entry.creation_height, WUtils.nodeTimeToInt64(input: entry.completion_time).millisecondsSince1970, entry.initial_balance, entry.balance, Date().millisecondsSince1970))
                    }
                    
                } else if (self.chainType == ChainType.IRIS_MAIN) {
                    guard let rawData = res as? [String : Any], rawData["error"] == nil, rawData["code"] == nil else {
                        self.onFetchFinished()
                        return
                    }
                    let unbondinginfo = UnbondingInfo(rawData)
                    let unbondingBalance = WUtils.plainStringToDecimal(unbondinginfo.balance.replacingOccurrences(of: "iris", with: "")).multiplying(byPowerOf10: 18, withBehavior: WUtils.handler0)
                    let initialBalance = WUtils.plainStringToDecimal(unbondinginfo.initial_balance.replacingOccurrences(of: "iris", with: "")).multiplying(byPowerOf10: 18, withBehavior: WUtils.handler0)
                    self.mUnbondings.append(Unbonding(account.account_id, unbondinginfo.validator_addr, unbondinginfo.creation_height, WUtils.nodeTimeToInt64(input: unbondinginfo.min_time).millisecondsSince1970, initialBalance.stringValue, unbondingBalance.stringValue, Date().millisecondsSince1970))
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchSignleUnBondingInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchRewardInfo(_ account: Account, _ validator: Validator) {
        var url: String?
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_REWARD_FROM_VAL + account.account_address + COSMOS_URL_REWARD_FROM_VAL_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_REWARD_FROM_VAL + account.account_address + KAVA_REWARD_FROM_VAL_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_REWARD_FROM_VAL + account.account_address + KAVA_TEST_REWARD_FROM_VAL_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_REWARD_FROM_VAL + account.account_address + BAND_REWARD_FROM_VAL_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_REWARD_FROM_VAL + account.account_address + IOV_REWARD_FROM_VAL_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.IOV_TEST) {
            url = IOV_TEST_REWARD_FROM_VAL + account.account_address + IOV_TEST_REWARD_FROM_VAL_TAIL + "/" + validator.operator_address
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_REWARD_FROM_VAL + account.account_address + CERTIK_TEST_REWARD_FROM_VAL_TAIL + "/" + validator.operator_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.KAVA_MAIN || self.chainType == ChainType.KAVA_TEST ||
                    self.chainType == ChainType.BAND_MAIN || self.chainType == ChainType.IOV_MAIN || self.chainType == ChainType.IOV_TEST || self.chainType == ChainType.CERTIK_TEST) {
                    guard let responseData = res as? NSDictionary,
                        let rawRewards = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        self.onFetchFinished()
                        return;
                    }
                    let reward = Reward.init()
                    reward.reward_v_address = validator.operator_address
                    for rawReward in rawRewards {
                        reward.reward_amount.append(Coin(rawReward as! [String : Any]))
                    }
                    self.mRewards.append(reward)
                }
                    
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchRewardInfo ", error) }
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
                if (SHOW_LOG) { print("onFetchIrisReward ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchApiHistory(_ account: Account, _ validator: Validator) {
        var url: String?
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_API_HISTORY + account.account_address + "/" + validator.operator_address
        } else if (chainType == ChainType.IRIS_MAIN) {
            url = IRIS_API_HISTORY + account.account_address + "/" + validator.operator_address
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_API_HISTORY + account.account_address + "/" + validator.operator_address
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_API_HISTORY + account.account_address + "/" + validator.operator_address
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_API_HISTORY + account.account_address + "/" + validator.operator_address
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_API_HISTORY + account.account_address + "/" + validator.operator_address
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_API_HISTORY + account.account_address + "/" + validator.operator_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["limit":"50"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                self.mApiHistories.removeAll()
                guard let histories = res as? Array<NSDictionary> else {
                    print("no history!!")
                    self.onFetchFinished()
                    return;
                }
                for rawHistory in histories {
                    self.mApiHistories.append(ApiHistory.HistoryData.init(rawHistory))
                }
                print("mApiHistories.count ", self.mApiHistories.count)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchApiHistory ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchSelfBondRate(_ address: String, _ vAddress: String) {
        var url: String?
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_BONDING + address + COSMOS_URL_BONDING_TAIL + "/" + vAddress
        } else if (chainType == ChainType.IRIS_MAIN) {
            url = IRIS_LCD_URL_BONDING + address + IRIS_LCD_URL_BONDING_TAIL + "/" + vAddress
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_BONDING + address + KAVA_BONDING_TAIL + "/" + vAddress
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_BONDING + address + KAVA_TEST_BONDING_TAIL + "/" + vAddress
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_BONDING + address + BAND_BONDING_TAIL + "/" + vAddress
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_BONDING + address + IOV_BONDING_TAIL + "/" + vAddress
        } else if (chainType == ChainType.IOV_TEST) {
            url = IOV_TEST_BONDING + address + IOV_TEST_BONDING_TAIL + "/" + vAddress
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_BONDING + address + CERTIK_TEST_BONDING_TAIL + "/" + vAddress
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.KAVA_MAIN || self.chainType == ChainType.KAVA_TEST ||
                    self.chainType == ChainType.BAND_MAIN || self.chainType == ChainType.IOV_MAIN || self.chainType == ChainType.IOV_TEST || self.chainType == ChainType.CERTIK_TEST) {
                    guard let responseData = res as? NSDictionary,
                        let rawData = responseData.object(forKey: "result") as? [String : Any] else {
                        self.onFetchFinished()
                        return;
                    }
                    self.mSelfBondingShare = BondingInfo(rawData).shares
                    
                } else if (self.chainType == ChainType.IRIS_MAIN) {
                    guard let rawData = res as? [String : Any], rawData["error"] == nil else {
                        self.onFetchFinished()
                        return
                    }
                    self.mSelfBondingShare = BondingInfo(rawData).shares
                }

            case .failure(let error):
                if (SHOW_LOG) { print("onFetchSelfBondRate ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchRedelegatedState(_ address: String, _ to: String) {
        var url: String?
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_REDELEGATION;
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_REDELEGATION;
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_REDELEGATION;
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_REDELEGATION;
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_REDELEGATION;
        } else if (chainType == ChainType.IOV_TEST) {
            url = IOV_TEST_REDELEGATION;
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_REDELEGATION;
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["delegator":address, "validator_to":to], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.KAVA_MAIN || self.chainType == ChainType.KAVA_TEST ||
                    self.chainType == ChainType.BAND_MAIN || self.chainType == ChainType.IOV_MAIN || self.chainType == ChainType.IOV_TEST || self.chainType == ChainType.CERTIK_TEST) {
                    if let responseData = res as? NSDictionary,
                        let redelegateHistories = responseData.object(forKey: "result") as? Array<NSDictionary> {
                        if (redelegateHistories.count > 0) {
                            self.onShowToast(NSLocalizedString("error_redelegation_limitted", comment: ""))
                        } else {
                            self.onStartRedelegate()
                        }
                    } else {
                        self.onStartRedelegate()
                    }
                }
                
            case .failure(let error):
                print("onFetchRedelegatedState ", error)
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            }
        }
    }
    
    func onFetchIrisRedelegateState(_ account:Account) {
        let request = Alamofire.request(IRIS_LCD_URL_REDELEGATION + account.account_address + IRIS_LCD_URL_REDELEGATION_TAIL, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let irisRedelegateState = res as? Array<NSDictionary> else {
                    return
                }
                self.mIrisRedelegate = irisRedelegateState
                for irisRedelegate in self.mIrisRedelegate! {
                    if let dstAddress = irisRedelegate.value(forKey: "validator_dst_addr") as? String, dstAddress == self.mValidator?.operator_address {
                        self.onShowToast(NSLocalizedString("error_redelegation_limitted", comment: ""))
                        return
                    }
                }
                self.onStartRedelegate()
            case .failure(let error):
                print("onFetchIrisRedelegateState ", error)
            }
        }
    }
    
    func onFetchRewardAddress(_ accountAddr: String) {
        var url = ""
        if (chainType == ChainType.COSMOS_MAIN) {
            url = COSMOS_URL_REWARD_ADDRESS + accountAddr + COSMOS_URL_REWARD_ADDRESS_TAIL
        } else if (chainType == ChainType.IRIS_MAIN) {
            url = IRIS_LCD_URL_REWARD_ADDRESS + accountAddr + IRIS_LCD_URL_REWARD_ADDRESS_TAIL
        } else if (chainType == ChainType.KAVA_MAIN) {
            url = KAVA_REWARD_ADDRESS + accountAddr + KAVA_REWARD_ADDRESS_TAIL
        } else if (chainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_REWARD_ADDRESS + accountAddr + KAVA_TEST_REWARD_ADDRESS_TAIL
        } else if (chainType == ChainType.BAND_MAIN) {
            url = BAND_REWARD_ADDRESS + accountAddr + BAND_REWARD_ADDRESS_TAIL
        } else if (chainType == ChainType.IOV_MAIN) {
            url = IOV_REWARD_ADDRESS + accountAddr + IOV_REWARD_ADDRESS_TAIL
        } else if (chainType == ChainType.IOV_TEST) {
            url = IOV_TEST_REWARD_ADDRESS + accountAddr + IOV_TEST_REWARD_ADDRESS_TAIL
        } else if (chainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_REWARD_ADDRESS + accountAddr + CERTIK_TEST_REWARD_ADDRESS_TAIL
        }
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        
        if (chainType == ChainType.IRIS_MAIN) {
            request.responseString { (response) in
                switch response.result {
                case .success(let res):
                    guard let address = res as? String else {
                        self.onShowReInvsetFailDialog()
                        return;
                    }

                    let trimAddress = address.replacingOccurrences(of: "\"", with: "")
                    if (trimAddress == accountAddr) {
                        self.onStartReInvest()
                    } else {
                        self.onShowReInvsetFailDialog()
                    }
                    
                case .failure(let error):
                    if(SHOW_LOG) { print("onFetchRewardAddress ", error) }
                }
            }
        } else if (self.chainType == ChainType.COSMOS_MAIN || self.chainType == ChainType.KAVA_MAIN || self.chainType == ChainType.KAVA_TEST ||
            self.chainType == ChainType.BAND_MAIN || self.chainType == ChainType.IOV_MAIN || self.chainType == ChainType.IOV_TEST || self.chainType == ChainType.CERTIK_TEST) {
            request.responseJSON { (response) in
                switch response.result {
                case .success(let res):
//                    print("onFetchRewardAddress ", res)
                    guard let responseData = res as? NSDictionary,
                        let address = responseData.object(forKey: "result") as? String else {
                            self.onShowReInvsetFailDialog()
                            return;
                    }
                    let trimAddress = address.replacingOccurrences(of: "\"", with: "")
                    if (trimAddress == accountAddr) {
                        self.onStartReInvest()
                    } else {
                        self.onShowReInvsetFailDialog()
                    }
                    
                case .failure(let error):
                    if(SHOW_LOG) { print("onFetchRewardAddress ", error) }
                }
            }
        }
    }
    
    
    func onStartDelegate() {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        if (chainType == ChainType.COSMOS_MAIN) {
            if (WUtils.getTokenAmount(balances, COSMOS_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_available", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            if (WUtils.getTokenAmount(balances, IRIS_MAIN_DENOM).compare(NSDecimalNumber.init(string: "400000000000000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (WUtils.getDelegableAmount(balances, KAVA_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_delegable", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.BAND_MAIN) {
            if (WUtils.getTokenAmount(balances, BAND_MAIN_DENOM).compare(NSDecimalNumber.zero).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_available", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IOV_MAIN) {
            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(NSDecimalNumber.init(string: "200000")).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_available", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IOV_TEST) {
            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(NSDecimalNumber.init(string: "200000")).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_available", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            if (WUtils.getTokenAmount(balances, CERTIK_TEST_DENOM).compare(NSDecimalNumber.init(string: "20000")).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_available", comment: ""))
                return
            }
            
        } else {
            self.onShowToast(NSLocalizedString("error_support_soon", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mTargetValidator = mValidator
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST || chainType == ChainType.CERTIK_TEST) {
            txVC.mType = COSMOS_MSG_TYPE_DELEGATE
        } else if (chainType == ChainType.IRIS_MAIN) {
            txVC.mType = IRIS_MSG_TYPE_DELEGATE
        }
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
        
    }
    
    func onStartUndelegate() {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        if (mBonding == nil || self.mBonding!.getBondingAmount(mValidator!) == NSDecimalNumber.zero) {
            self.onShowToast(NSLocalizedString("error_not_undelegate", comment: ""))
            return
        }
        
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST || chainType == ChainType.BAND_MAIN || chainType == ChainType.CERTIK_TEST) {
            if (mUnbondings.count >= 7) {
                self.onShowToast(NSLocalizedString("error_unbonding_count_over", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            if (mUnbondings.count >= 1) {
                self.onShowToast(NSLocalizedString("error_unbonding_count_over", comment: ""))
                return
            }
            if (WUtils.getTokenAmount(balances, IRIS_MAIN_DENOM).compare(NSDecimalNumber.init(string: "400000000000000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        }
        
        if (chainType == ChainType.IOV_MAIN) {
            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(NSDecimalNumber.init(string: "200000")).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IOV_TEST) {
            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(NSDecimalNumber.init(string: "200000")).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            if (WUtils.getTokenAmount(balances, CERTIK_TEST_DENOM).compare(NSDecimalNumber.init(string: "20000")).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mTargetValidator = mValidator
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST || chainType == ChainType.CERTIK_TEST) {
            txVC.mType = COSMOS_MSG_TYPE_UNDELEGATE2
        } else if (chainType == ChainType.IRIS_MAIN) {
            txVC.mType = IRIS_MSG_TYPE_UNDELEGATE
        }
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
        
    }
    
    func onCheckRedelegate() {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        if (mBonding == nil || self.mBonding!.getBondingAmount(mValidator!) == NSDecimalNumber.zero) {
            self.onShowToast(NSLocalizedString("error_not_redelegate", comment: ""))
            return
        }

        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN) {
            self.onFetchRedelegatedState(account!.account_address, mValidator!.operator_address)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            if (WUtils.getTokenAmount(balances, IRIS_MAIN_DENOM).compare(NSDecimalNumber.init(string: "520000000000000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            self.onFetchIrisRedelegateState(account!)
        } else if (chainType == ChainType.IOV_MAIN) {
            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(NSDecimalNumber.init(string: "300000")).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_available", comment: ""))
                return
            }
            self.onFetchRedelegatedState(account!.account_address, mValidator!.operator_address)
            
        } else if (chainType == ChainType.IOV_TEST) {
            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(NSDecimalNumber.init(string: "300000")).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_available", comment: ""))
                return
            }
            self.onFetchRedelegatedState(account!.account_address, mValidator!.operator_address)
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            if (WUtils.getTokenAmount(balances, CERTIK_TEST_DENOM).compare(NSDecimalNumber.init(string: "30000")).rawValue <= 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_available", comment: ""))
                return
            }
            self.onFetchRedelegatedState(account!.account_address, mValidator!.operator_address)
            
        } else {
            self.onShowToast(NSLocalizedString("error_support_soon", comment: ""))
            return
        }
    }
    
    func onStartRedelegate() {
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mTargetValidator = mValidator
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST || chainType == ChainType.CERTIK_TEST) {
            txVC.mType = COSMOS_MSG_TYPE_REDELEGATE2
        } else if (chainType == ChainType.IRIS_MAIN) {
            txVC.mirisRedelegate = mIrisRedelegate
            txVC.mType = IRIS_MSG_TYPE_REDELEGATE
        }
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onStartGetSingleReward() {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        if (chainType == ChainType.COSMOS_MAIN) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, COSMOS_MAIN_DENOM)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum.compare(NSDecimalNumber.one).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            if (mIrisRewards != nil) {
                let rewardSum = mIrisRewards?.getPerValReward(valOp: mValidator!.operator_address)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum!.compare(NSDecimalNumber(string: "400000000000000000")).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            if (WUtils.getTokenAmount(balances, IRIS_MAIN_DENOM).compare(NSDecimalNumber.init(string: "400000000000000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, KAVA_MAIN_DENOM)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum.compare(NSDecimalNumber.one).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }

            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.BAND_MAIN) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, BAND_MAIN_DENOM)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum.compare(NSDecimalNumber.one).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IOV_MAIN) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, IOV_MAIN_DENOM)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum.compare(NSDecimalNumber.init(string: "150000")).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(NSDecimalNumber.init(string: "150000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IOV_TEST) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, IOV_TEST_DENOM)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum.compare(NSDecimalNumber.init(string: "150000")).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(NSDecimalNumber.init(string: "150000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, CERTIK_TEST_DENOM)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum.compare(NSDecimalNumber.init(string: "15000")).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            if (WUtils.getTokenAmount(balances, CERTIK_TEST_DENOM).compare(NSDecimalNumber.init(string: "15000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else {
            self.onShowToast(NSLocalizedString("error_support_soon", comment: ""))
            return
        }
        
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        var validators = Array<Validator>()
        validators.append(mValidator!)
        txVC.mRewardTargetValidators = validators
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST ||
            chainType == ChainType.BAND_MAIN || chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST || chainType == ChainType.CERTIK_TEST) {
            txVC.mType = COSMOS_MSG_TYPE_WITHDRAW_DEL
        } else if (chainType == ChainType.IRIS_MAIN) {
            txVC.mType = IRIS_MSG_TYPE_WITHDRAW
        }
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
        
    }
    
    func onCheckReinvest() {
        if(!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return
        }
        
        let balances = BaseData.instance.selectBalanceById(accountId: account!.account_id)
        if (chainType == ChainType.COSMOS_MAIN) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, COSMOS_MAIN_DENOM)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum.compare(NSDecimalNumber.one).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            if (mIrisRewards != nil) {
                let rewardSum = mIrisRewards?.getPerValReward(valOp: mValidator!.operator_address)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum!.compare(NSDecimalNumber(string: "400000000000000000")).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            if (WUtils.getTokenAmount(balances, IRIS_MAIN_DENOM).compare(NSDecimalNumber.init(string: "400000000000000000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, KAVA_MAIN_DENOM)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum.compare(NSDecimalNumber.one).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.BAND_MAIN) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, BAND_MAIN_DENOM)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum.compare(NSDecimalNumber.one).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IOV_MAIN) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, IOV_MAIN_DENOM)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum.compare(NSDecimalNumber.init(string: "300000")).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            if (WUtils.getTokenAmount(balances, IOV_MAIN_DENOM).compare(NSDecimalNumber.init(string: "300000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else if (chainType == ChainType.IOV_TEST) {
            if (mRewards.count > 0) {
                let rewardSum = WUtils.getAllRewardByDenom(mRewards, IOV_TEST_DENOM)
                if (rewardSum == NSDecimalNumber.zero) {
                    self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                    return
                }
                if (rewardSum.compare(NSDecimalNumber.init(string: "300000")).rawValue < 0) {
                    self.onShowToast(NSLocalizedString("error_wasting_fee", comment: ""))
                    return
                }
                
            } else {
                self.onShowToast(NSLocalizedString("error_not_reward", comment: ""))
                return
            }
            if (WUtils.getTokenAmount(balances, IOV_TEST_DENOM).compare(NSDecimalNumber.init(string: "300000")).rawValue < 0) {
                self.onShowToast(NSLocalizedString("error_not_enough_fee", comment: ""))
                return
            }
            
        } else {
            self.onShowToast(NSLocalizedString("error_support_soon", comment: ""))
            return
        }
        self.onFetchRewardAddress(account!.account_address)
    }
    
    func onStartReInvest() {
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mTargetValidator = self.mValidator
        txVC.mType = COSMOS_MULTI_MSG_TYPE_REINVEST
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    func onShowReInvsetFailDialog() {
        let alert = UIAlertController(title: NSLocalizedString("error_reward_address_changed_title", comment: ""), message: NSLocalizedString("error_reward_address_changed_msg", comment: ""), preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: NSLocalizedString("confirm", comment: ""), style: .cancel, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
}

