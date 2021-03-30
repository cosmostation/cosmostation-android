//
//  MainTabViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import Toast_Swift
import NotificationBannerSwift
import GRPC
import NIO
import SwiftProtobuf

class MainTabViewController: UITabBarController, UITabBarControllerDelegate, SBCardPopupDelegate, AccountSelectDelegate {
    
    var mAccount: Account!
    var mChainType: ChainType!
    var mAccounts = Array<Account>()
    var mBalances = Array<Balance>()
    var mAllValidator = Array<Validator>()
    var mTopValidators = Array<Validator>()
    var mOtherValidators = Array<Validator>()
    var mMyValidators = Array<Validator>()
    var mBondingList = Array<Bonding>()
    var mUnbondingList = Array<Unbonding>()
    var mRewardList = Array<Reward>()
    var mAtomTic: NSDictionary?
    var mPriceTic: NSDictionary?
    var mFetchCnt = 0
        
    var waitAlert: UIAlertController?
    var banner: NotificationBanner?
    var notiView: NotificationView?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.notiView = NotificationView()
        
        self.onUpdateAccountDB()
        self.onFetchAccountData()
        
        self.delegate = self
        self.selectedIndex = BaseData.instance.getLastTab()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        if( self.mFetchCnt > 0)  {
            self.showWaittingAlert()
        }
        NotificationCenter.default.addObserver(self, selector: #selector(self.showNotificationBanner(_:)), name: Notification.Name("pushNoti"), object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("pushNoti"), object: nil)
    }
    
    @objc public func showNotificationBanner(_ notification: NSNotification) {
        guard let userInfo = notification.userInfo as? [String: Any] else {
            return
        }
        
        if let notifyto = userInfo["notifyto"] as? String,
            let txid = userInfo["txid"] as? String,
            let type = userInfo["type"] as? String,
            let aps = userInfo["aps"] as? NSDictionary,
            let alert = aps["alert"] as? NSDictionary,
            let title = alert["title"] as? String,
            let body = alert["body"] as? String {
            
            if (type == "sent") {
                notiView!.notiType.image = UIImage.init(named: "notificationsSend")
                notiView!.notiTitle.textColor = UIColor.init(hexString: "#f31963")
                
            } else if (type == "received") {
                notiView!.notiType.image = UIImage.init(named: "notificationsReceive")
                notiView!.notiTitle.textColor = UIColor.init(hexString: "#37cc6e")
            } else {
                return
            }
            
            notiView!.notiTitle.text = title
            notiView!.notiMsg.text = body
            notiView!.actionDismiss = {
                self.banner?.dismiss()
            }
            notiView!.actionBody = {
                let notiAccount = BaseData.instance.selectAccountByAddress(address: notifyto)
                if (notiAccount != nil) {
                    BaseData.instance.setRecentAccountId(notiAccount!.account_id)
                    BaseData.instance.setLastTab(2)
                    
                    let mainTabVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "MainTabViewController") as! MainTabViewController
                    let appDelegate = UIApplication.shared.delegate as! AppDelegate
                    appDelegate.window?.rootViewController = mainTabVC
                    self.present(mainTabVC, animated: true, completion: nil)
                }
                self.banner?.dismiss()
            }
            banner = NotificationBanner(customView: notiView!)
            banner?.dismissDuration = 0.5
            banner?.show()
        }
    }
    
    
    func tabBarController(_ tabBarController: UITabBarController, didSelect viewController: UIViewController) {
        BaseData.instance.setLastTab(tabBarController.selectedIndex)
    }
    
    func onShowAccountSwicth() {
        let sourceVC = self.selectedViewController!
        let accountSelectVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "AccountSelectViewController") as! AccountSelectViewController
        accountSelectVC.modalPresentationStyle = .overFullScreen
        accountSelectVC.resultDelegate = self

        sourceVC.view.superview?.insertSubview(accountSelectVC.view, aboveSubview: sourceVC.view)
        accountSelectVC.view.transform = CGAffineTransform(translationX: 0, y: -sourceVC.view.frame.size.height)
        UIView.animate(withDuration: 0.3, animations: {
            accountSelectVC.view.transform = CGAffineTransform(translationX: 0, y: 0)
            }) { (Finished) in
                sourceVC.present(accountSelectVC, animated: false, completion: nil)
        }
    }
    
    func SBCardPopupResponse(type:Int, result: Int) {
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(490), execute: {
            let naviVC = self.selectedViewController as? UINavigationController
            var tagetVC:BaseViewController?
            if(result == 1) {
                tagetVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "CreateViewController") as! CreateViewController
                tagetVC?.chainType = self.targetChain
                
            } else if(result == 2) {
                tagetVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "RestoreViewController") as! RestoreViewController
                tagetVC?.chainType = self.targetChain
                
            } else if(result == 3) {
                tagetVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "AddAddressViewController") as! AddAddressViewController
                
            }
            if(tagetVC != nil) {
                tagetVC?.hidesBottomBarWhenPushed = true
                naviVC?.navigationItem.title = ""
                naviVC?.pushViewController(tagetVC!, animated: true)
            }
        })
    }
    
    
    func onUpdateAccountDB() {
        mAccount = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        mAccounts = BaseData.instance.selectAllAccounts()
        if (mAccount == nil && mAccounts.count > 0) {
            mAccount = mAccounts[0]
            BaseData.instance.setRecentAccountId(mAccount.account_id)
        }
        if (mAccount == nil) {
            print("NO ACCOUNT ERROR!!!!")
            return
        }
        mChainType = WUtils.getChainType(mAccount.account_base_chain)
    }
    
    func onFetchAccountData() -> Bool {
        if (self.mFetchCnt > 0)  {
            return false
        }
        self.mAllValidator.removeAll()
        self.mTopValidators.removeAll()
        self.mOtherValidators.removeAll()
        self.mMyValidators.removeAll()
        self.mRewardList.removeAll()
        
        BaseData.instance.mNodeInfo = nil
        BaseData.instance.mAllValidator.removeAll()
        BaseData.instance.mTopValidator.removeAll()
        BaseData.instance.mOtherValidator.removeAll()
        BaseData.instance.mMyValidator.removeAll()
        
        //gRPC
        BaseData.instance.mNodeInfo_gRPC = nil
        BaseData.instance.mAccount_gRPC = nil
        BaseData.instance.mAllValidators_gRPC.removeAll()
        BaseData.instance.mBondedValidators_gRPC.removeAll()
        BaseData.instance.mUnbondValidators_gRPC.removeAll()
        BaseData.instance.mMyValidators_gRPC.removeAll()
        
        BaseData.instance.mMyDelegations_gRPC.removeAll()
        BaseData.instance.mMyUnbondings_gRPC.removeAll()
        BaseData.instance.mMyBalances_gRPC.removeAll()
        BaseData.instance.mMyVestings_gRPC.removeAll()
        BaseData.instance.mMyReward_gRPC.removeAll()
        
        BaseData.instance.mMintParam_gRPC = nil
        BaseData.instance.mStakingPool_gRPC = nil
        BaseData.instance.mProvision_gRPC = NSDecimalNumber.zero
        BaseData.instance.mInflation_gRPC = NSDecimalNumber.zero
        
        BaseData.instance.mIrisMintParam_gRPC = nil
        BaseData.instance.mIrisTokens_gRPC.removeAll()
        
        if (mChainType == ChainType.COSMOS_MAIN) {
            self.mFetchCnt = 13
            onFetchgRPCNodeInfo()
            onFetchgRPCAuth(mAccount.account_address)
            onFetchgRPCBondedValidators(0)
            onFetchgRPCUnbondedValidators(0)
            onFetchgRPCUnbondingValidators(0)

            onFetchgRPCBalance(mAccount.account_address, 0)
            onFetchgRPCDelegations(mAccount.account_address, 0)
            onFetchgRPCUndelegations(mAccount.account_address, 0)
            onFetchgRPCRewards(mAccount.account_address, 0)

            onFetchgRPCMintParam()
            onFetchgRPCInflation()
            onFetchgRPCProvision()
            onFetchgRPCStakingPool()
            
//            onFetchgRPCAuth(mAccount)
            
        } else if (mChainType == ChainType.IRIS_MAIN) {
            self.mFetchCnt = 12
            onFetchgRPCNodeInfo()
            onFetchgRPCAuth(mAccount.account_address)
            onFetchgRPCBondedValidators(0)
            onFetchgRPCUnbondedValidators(0)
            onFetchgRPCUnbondingValidators(0)
            
            onFetchgRPCBalance(mAccount.account_address, 0)
            onFetchgRPCDelegations(mAccount.account_address, 0)
            onFetchgRPCUndelegations(mAccount.account_address, 0)
            onFetchgRPCRewards(mAccount.account_address, 0)
            
            onFetchgRPCStakingPool()
            onFetchgRPCIrisMintParam()
            onFetchgRPCIrisTokens()
            
        } else if (mChainType == ChainType.BINANCE_MAIN || mChainType == ChainType.BINANCE_TEST) {
            self.mFetchCnt = 4
            BaseData.instance.mBnbTokenList.removeAll()
            onFetchNodeInfo()
            onFetchAccountInfo(mAccount)
            onFetchBnbTokens()
            onFetchBnbMiniTokens()
            
        } else if (mChainType == ChainType.KAVA_MAIN || mChainType == ChainType.KAVA_TEST) {
            self.mFetchCnt = 13
            BaseData.instance.mCdpParam = nil
            onFetchNodeInfo()
            onFetchTopValidatorsInfo()
            onFetchUnbondedValidatorsInfo()
            onFetchUnbondingValidatorsInfo()
            
            onFetchAccountInfo(mAccount)
            onFetchBondingInfo(mAccount)
            onFetchUnbondingInfo(mAccount)
            
            onFetchMintParam()
            onFetchInflation()
            onFetchProvision()
            onFetchStakingPool()
            
            onFetchPriceFeedParam()
            onFetchIncentiveParam()
            
        } else if (mChainType == ChainType.BAND_MAIN) {
            self.mFetchCnt = 12
            onFetchNodeInfo()
            onFetchTopValidatorsInfo()
            onFetchUnbondedValidatorsInfo()
            onFetchUnbondingValidatorsInfo()
            
            onFetchAccountInfo(mAccount)
            onFetchBondingInfo(mAccount)
            onFetchUnbondingInfo(mAccount)
            
            onFetchMintParam()
            onFetchInflation()
            onFetchProvision()
            onFetchStakingPool()
            onFetchBandOracleStatus()
            
        } else if (mChainType == ChainType.SECRET_MAIN) {
            self.mFetchCnt = 11
            onFetchNodeInfo()
            onFetchTopValidatorsInfo()
            onFetchUnbondedValidatorsInfo()
            onFetchUnbondingValidatorsInfo()
            
            onFetchAccountInfo(mAccount)
            onFetchBondingInfo(mAccount)
            onFetchUnbondingInfo(mAccount)
            
            onFetchMintParam()
            onFetchInflation()
            onFetchProvision()
            onFetchStakingPool()
            
        } else if (mChainType == ChainType.IOV_MAIN || mChainType == ChainType.IOV_TEST) {
            self.mFetchCnt = 13
            onFetchNodeInfo()
            onFetchTopValidatorsInfo()
            onFetchUnbondedValidatorsInfo()
            onFetchUnbondingValidatorsInfo()
            
            onFetchAccountInfo(mAccount)
            onFetchBondingInfo(mAccount)
            onFetchUnbondingInfo(mAccount)
            
            onFetchMintParam()
            onFetchInflation()
            onFetchProvision()
            onFetchStakingPool()
            
            onFetchStarNameFees()
            onFetchStarNameConfig()
            
        } else if (mChainType == ChainType.OKEX_MAIN || mChainType == ChainType.OKEX_TEST) {
            self.mFetchCnt = 8
            BaseData.instance.mOkStaking = nil
            BaseData.instance.mOkUnbonding = nil
            BaseData.instance.mOkTokenList = nil
            onFetchNodeInfo()
            onFetchAllValidatorsInfo();
            
            onFetchAccountInfo(mAccount)
            onFetchOkAccountBalance(mAccount)
            onFetchOkTokenList()
            onFetchOkDexTicker()
            
            onFetchOkStakingInfo(mAccount)
            onFetchOkUnbondingInfo(mAccount)
            
            
        } else if (mChainType == ChainType.CERTIK_MAIN || mChainType == ChainType.CERTIK_TEST) {
            self.mFetchCnt = 11
            onFetchNodeInfo()
            onFetchTopValidatorsInfo()
            onFetchUnbondedValidatorsInfo()
            onFetchUnbondingValidatorsInfo()
            onFetchAccountInfo(mAccount)
            onFetchBondingInfo(mAccount)
            onFetchUnbondingInfo(mAccount)
            
            onFetchMintParam()
            onFetchInflation()
            onFetchProvision()
            onFetchStakingPool()
            
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            self.mFetchCnt = 11
            onFetchNodeInfo()
            onFetchTopValidatorsInfo()
            onFetchUnbondedValidatorsInfo()
            onFetchUnbondingValidatorsInfo()
            
            onFetchAccountInfo(mAccount)
            onFetchBondingInfo(mAccount)
            onFetchUnbondingInfo(mAccount)
            
            onFetchMintParam()
            onFetchInflation()
            onFetchProvision()
            onFetchStakingPool()
            
        }

        
        else if (mChainType == ChainType.AKASH_MAIN || mChainType == ChainType.PERSIS_MAIN ) {
            self.mFetchCnt = 13
            onFetchgRPCNodeInfo()
            onFetchgRPCAuth(mAccount.account_address)
            onFetchgRPCBondedValidators(0)
            onFetchgRPCUnbondedValidators(0)
            onFetchgRPCUnbondingValidators(0)

            onFetchgRPCBalance(mAccount.account_address, 0)
            onFetchgRPCDelegations(mAccount.account_address, 0)
            onFetchgRPCUndelegations(mAccount.account_address, 0)
            onFetchgRPCRewards(mAccount.account_address, 0)

            onFetchgRPCMintParam()
            onFetchgRPCInflation()
            onFetchgRPCProvision()
            onFetchgRPCStakingPool()
            
        } else if (mChainType == ChainType.COSMOS_TEST) {
            self.mFetchCnt = 13
            onFetchgRPCNodeInfo()
            onFetchgRPCAuth(mAccount.account_address)
            onFetchgRPCBondedValidators(0)
            onFetchgRPCUnbondedValidators(0)
            onFetchgRPCUnbondingValidators(0)

            onFetchgRPCBalance(mAccount.account_address, 0)
            onFetchgRPCDelegations(mAccount.account_address, 0)
            onFetchgRPCUndelegations(mAccount.account_address, 0)
            onFetchgRPCRewards(mAccount.account_address, 0)

            onFetchgRPCMintParam()
            onFetchgRPCInflation()
            onFetchgRPCProvision()
            onFetchgRPCStakingPool()
            
        } else if (mChainType == ChainType.IRIS_TEST) {
            self.mFetchCnt = 12
            onFetchgRPCNodeInfo()
            onFetchgRPCAuth(mAccount.account_address)
            onFetchgRPCBondedValidators(0)
            onFetchgRPCUnbondedValidators(0)
            onFetchgRPCUnbondingValidators(0)
            
            onFetchgRPCBalance(mAccount.account_address, 0)
            onFetchgRPCDelegations(mAccount.account_address, 0)
            onFetchgRPCUndelegations(mAccount.account_address, 0)
            onFetchgRPCRewards(mAccount.account_address, 0)
            
            onFetchgRPCStakingPool()
            onFetchgRPCIrisMintParam()
            onFetchgRPCIrisTokens()
            
        }
        onFetchPriceTic(false)
        return true
    }
    
    func onFetchFinished() {
//        print("onFetchFinished ", self.mFetchCnt)
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt > 0) { return }
        if (mChainType == ChainType.BINANCE_MAIN || mChainType == ChainType.BINANCE_TEST) {
            mAccount    = BaseData.instance.selectAccountById(id: mAccount!.account_id)
            mBalances   = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
            NotificationCenter.default.post(name: Notification.Name("onFetchDone"), object: nil, userInfo: nil)
            self.hideWaittingAlert()
            return
            
        } else if (WUtils.isGRPC(mChainType!)) {
            BaseData.instance.mAllValidators_gRPC.append(contentsOf: BaseData.instance.mBondedValidators_gRPC)
            BaseData.instance.mAllValidators_gRPC.append(contentsOf: BaseData.instance.mUnbondValidators_gRPC)
            for validator in BaseData.instance.mAllValidators_gRPC {
                var mine = false;
                for delegation in BaseData.instance.mMyDelegations_gRPC {
                    if (delegation.delegation.validatorAddress == validator.operatorAddress) {
                        mine = true;
                        break;
                    }
                }
                for unbonding in BaseData.instance.mMyUnbondings_gRPC {
                    if (unbonding.validatorAddress == validator.operatorAddress) {
                        mine = true;
                        break;
                    }
                }
                if (mine) {
                    BaseData.instance.mMyValidators_gRPC.append(validator)
                }
            }
            
            if (SHOW_LOG) {
                print("BaseData.instance.mAllValidators_gRPC ", BaseData.instance.mAllValidators_gRPC.count)
                print("BaseData.instance.mBondedValidators_gRPC ", BaseData.instance.mBondedValidators_gRPC.count)
                print("BaseData.instance.mUnbondValidators_gRPC ", BaseData.instance.mUnbondValidators_gRPC.count)
                print("BaseData.instance.mMyValidators_gRPC ", BaseData.instance.mMyValidators_gRPC.count)
                print("BaseData.instance.mMyBalances_gRPC ", BaseData.instance.mMyBalances_gRPC.count)
            }
            
            
            if (BaseData.instance.mNodeInfo_gRPC == nil) {
                self.onShowToast(NSLocalizedString("error_network", comment: ""))
            } else {
//                print("nodeInfo ", BaseData.instance.mNodeInfo_gRPC?.network)
//                print("authInfo ", BaseData.instance.mAccount_gRPC?.typeURL)
                if (BaseData.instance.mAccount_gRPC != nil && BaseData.instance.mAccount_gRPC!.typeURL.contains(Cosmos_Auth_V1beta1_BaseAccount.protoMessageName) == false) {
                    if (mChainType == ChainType.PERSIS_MAIN) {
                        WUtils.onParsePersisVestingAccount()
                    } else {
                        WUtils.onParseVestingAccount()
                    }
                    
                }
            }
            NotificationCenter.default.post(name: Notification.Name("onFetchDone"), object: nil, userInfo: nil)
            self.hideWaittingAlert()
            return
            
        } else if (mChainType == ChainType.OKEX_MAIN || mChainType == ChainType.OKEX_TEST) {
            mAccount    = BaseData.instance.selectAccountById(id: mAccount!.account_id)
            mBalances   = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
            
            for validator in mAllValidator {
                if (validator.status == validator.BONDED) {
                    mTopValidators.append(validator)
                } else {
                    mOtherValidators.append(validator)
                }
                if let validator_address = BaseData.instance.mOkStaking?.validator_address {
                    for myVal in validator_address {
                        if (validator.operator_address == myVal) {
                            self.mMyValidators.append(validator)
                        }
                    }
                }
            }
            
            BaseData.instance.mAllValidator = mAllValidator
            BaseData.instance.mTopValidator = mTopValidators
            BaseData.instance.mOtherValidator = mOtherValidators
            BaseData.instance.mMyValidator = mMyValidators
            
        } else {
            mAccount    = BaseData.instance.selectAccountById(id: mAccount!.account_id)
            mBalances   = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
            mBondingList = BaseData.instance.selectBondingById(accountId: mAccount!.account_id)
            mUnbondingList = BaseData.instance.selectUnbondingById(accountId: mAccount!.account_id)
            
            mAllValidator.append(contentsOf: mTopValidators)
            mAllValidator.append(contentsOf: mOtherValidators)
            
            for validator in mAllValidator {
                var mine = false;
                for bonding in mBondingList {
                    if (bonding.bonding_v_address == validator.operator_address) {
                        mine = true;
                        break;
                    }
                }
                for unbonding in mUnbondingList {
                    if (unbonding.unbonding_v_address == validator.operator_address) {
                        mine = true;
                        break;
                    }
                }
                if (mine) {
                    self.mMyValidators.append(validator)
                }
            }
        }
        
//        print("BaseData.instance.mAllValidator ", BaseData.instance.mAllValidator.count)
//        print("BaseData.instance.mTopValidator ", BaseData.instance.mTopValidator.count)
//        print("BaseData.instance.mOtherValidator ", BaseData.instance.mOtherValidator.count)
//        print("BaseData.instance.mMyValidator ", BaseData.instance.mMyValidator.count)
        
        if (BaseData.instance.mNodeInfo == nil || mAllValidator.count <= 0) {
            self.onShowToast(NSLocalizedString("error_network", comment: ""))
        } else {
            BaseData.instance.setAllValidators(mAllValidator)
//            print("nodeInfo ", BaseData.instance.mNodeInfo?.network)
        }
        NotificationCenter.default.post(name: Notification.Name("onFetchDone"), object: nil, userInfo: nil)
        self.hideWaittingAlert()

    }
    
    func onFetchNodeInfo() {
        var url: String?
        if (mChainType == ChainType.BINANCE_MAIN ) {
            url = BNB_URL_NODE_INFO
        } else if (mChainType == ChainType.OKEX_MAIN) {
            url = OKEX_NODE_INFO
        } else if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_NODE_INFO
        } else if (mChainType == ChainType.BAND_MAIN) {
            url = BAND_NODE_INFO
        } else if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_NODE_INFO
        } else if (mChainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_NODE_INFO
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = SECRET_NODE_INFO
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_NODE_INFO
        }
        else if (mChainType == ChainType.BINANCE_TEST) {
            url = BNB_TEST_URL_NODE_INFO
        } else if (mChainType == ChainType.OKEX_TEST) {
            url = OKEX_TEST_NODE_INFO
        } else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_NODE_INFO
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_NODE_INFO
        } else if (mChainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_NODE_INFO
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary, let nodeInfo = responseData.object(forKey: "node_info") as? NSDictionary else {
                    self.onFetchFinished()
                    return
                }
                BaseData.instance.mNodeInfo = NodeInfo.init(nodeInfo)
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchTopValidatorsInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchTopValidatorsInfo() {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_VALIDATORS
        } else if (mChainType == ChainType.BAND_MAIN) {
            url = BAND_VALIDATORS
        } else if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_VALIDATORS
        } else if (mChainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_VALIDATORS
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = SECRET_VALIDATORS
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_VALIDATORS
        }
        else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_VALIDATORS
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_VALIDATORS
        } else if (mChainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_VALIDATORS
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["status":"bonded"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary, let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return
                }
                for validator in validators {
                    self.mTopValidators.append(Validator(validator as! [String : Any]))
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchTopValidatorsInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchUnbondedValidatorsInfo() {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_VALIDATORS
        } else if (mChainType == ChainType.BAND_MAIN) {
            url = BAND_VALIDATORS
        } else if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_VALIDATORS
        } else if (mChainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_VALIDATORS
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = SECRET_VALIDATORS
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_VALIDATORS
        }
        else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_VALIDATORS
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_VALIDATORS
        } else if (mChainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_VALIDATORS
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["status":"unbonded"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary, let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return
                }
                for validator in validators {
                    self.mOtherValidators.append(Validator(validator as! [String : Any]))
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchUnbondedValidatorsInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchUnbondingValidatorsInfo() {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_VALIDATORS
        } else if (mChainType == ChainType.BAND_MAIN) {
            url = BAND_VALIDATORS
        } else if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_VALIDATORS
        } else if (mChainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_VALIDATORS
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = SECRET_VALIDATORS
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_VALIDATORS
        }
        else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_VALIDATORS
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_VALIDATORS
        } else if (mChainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_VALIDATORS
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["status":"unbonding"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary, let validators = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return
                }
                for validator in validators {
                    self.mOtherValidators.append(Validator(validator as! [String : Any]))
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchUnbondingValidatorsInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchAllValidatorsInfo() {
        var url: String?
        if (mChainType == ChainType.OKEX_MAIN) {
            url = OKEX_VALIDATORS
        } else if (mChainType == ChainType.OKEX_TEST) {
            url = OKEX_TEST_VALIDATORS
        }
        let request = Alamofire.request(url!, method: .get, parameters: ["status":"all"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let validators = res as? Array<NSDictionary> else {
                    self.onFetchFinished()
                    return
                }
                for validator in validators {
                    self.mAllValidator.append(Validator(validator as! [String : Any]))
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchAllValidatorsInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    
    func onFetchAccountInfo(_ account: Account) {
        var url: String?
        if (mChainType == ChainType.BINANCE_MAIN ) {
            url = BNB_URL_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_ACCOUNT_INFO + account.account_address
        }  else if (mChainType == ChainType.BAND_MAIN) {
            url = BAND_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = SECRET_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.OKEX_MAIN) {
            url = OKEX_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_ACCOUNT_INFO + account.account_address
        }
        else if (mChainType == ChainType.BINANCE_TEST) {
            url = BNB_TEST_URL_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.KAVA_TEST) {
           url = KAVA_TEST_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.OKEX_TEST) {
            url = OKEX_TEST_ACCOUNT_INFO + account.account_address
        } else if (mChainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_ACCOUNT_INFO + account.account_address
        }
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if (self.mChainType == ChainType.BINANCE_MAIN || self.mChainType == ChainType.BINANCE_TEST) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.onFetchFinished()
                        return
                    }
                    let bnbAccountInfo = BnbAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithBnbAccountInfo(account, bnbAccountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithBnbAccountInfo(account, bnbAccountInfo))
                    
                } else if (self.mChainType == ChainType.KAVA_MAIN || self.mChainType == ChainType.KAVA_TEST) {
                    guard let info = res as? [String : Any] else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.onFetchFinished()
                        return
                    }
                    let kavaAccountInfo = KavaAccountInfo.init(info)
                    BaseData.instance.mKavaAccountResult = kavaAccountInfo.result
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithKavaAccountInfo(account, kavaAccountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithKavaAccountInfo(account, kavaAccountInfo))
                    
                } else if (self.mChainType == ChainType.OKEX_MAIN || self.mChainType == ChainType.OKEX_TEST) {
                    guard let info = res as? NSDictionary else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.onFetchFinished()
                        return
                    }
                    let okAccountInfo = OkAccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithOkAccountInfo(account, okAccountInfo))
                    BaseData.instance.mOkAccountInfo = okAccountInfo
                    
                } else if (self.mChainType == ChainType.SENTINEL_MAIN) {
                    guard let info = res as? NSDictionary else {
                        _ = BaseData.instance.deleteBalance(account: account)
                        self.onFetchFinished()
                        return
                    }
                    let vestingAccountInfo = VestingAccountInfo.init(info)
                    BaseData.instance.mVestingAccountInfoResult = vestingAccountInfo.result
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithVestingAccountInfo(account, vestingAccountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithVestingAccountInfo(account, vestingAccountInfo))
                    
                } else {
                    guard let responseData = res as? NSDictionary,
                        let info = responseData.object(forKey: "result") as? [String : Any] else {
                            _ = BaseData.instance.deleteBalance(account: account)
                            self.onFetchFinished()
                            return
                    }
                    let accountInfo = AccountInfo.init(info)
                    _ = BaseData.instance.updateAccount(WUtils.getAccountWithAccountInfo(account, accountInfo))
                    BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithAccountInfo(account, accountInfo))
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchAccountInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchBondingInfo(_ account: Account) {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_BONDING + account.account_address + KAVA_BONDING_TAIL
        } else if (mChainType == ChainType.BAND_MAIN) {
            url = BAND_BONDING + account.account_address + BAND_BONDING_TAIL
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = SECRET_BONDING + account.account_address + SECRET_BONDING_TAIL
        } else if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_BONDING + account.account_address + IOV_BONDING_TAIL
        } else if (mChainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_BONDING + account.account_address + CERTIK_BONDING_TAIL
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_BONDING + account.account_address + SENTINEL_BONDING_TAIL
        }
        else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_BONDING + account.account_address + KAVA_TEST_BONDING_TAIL
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_BONDING + account.account_address + IOV_TEST_BONDING_TAIL
        } else if (mChainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_BONDING + account.account_address + CERTIK_TEST_BONDING_TAIL
        }
        let tempAddress = account.account_address
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                    let bondinginfos = responseData.object(forKey: "result") as? Array<NSDictionary>,
                    bondinginfos.count > 0  else {
                        _ = BaseData.instance.deleteBonding(account: account)
                        self.onFetchFinished()
                        return;
                }
                let mTempBondings = WUtils.getBondingwithBondingInfo(account, bondinginfos, self.mChainType)
                BaseData.instance.updateBondings(mTempBondings)
                self.mFetchCnt = self.mFetchCnt + mTempBondings.count
                for bondig in mTempBondings {
                    self.onFetchEachReward(tempAddress, bondig.bonding_v_address)
                }
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchBondingInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchUnbondingInfo(_ account: Account) {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_UNBONDING + account.account_address + KAVA_UNBONDING_TAIL
        }  else if (mChainType == ChainType.BAND_MAIN) {
            url = BAND_UNBONDING + account.account_address + BAND_UNBONDING_TAIL
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = SECRET_UNBONDING + account.account_address + SECRET_UNBONDING_TAIL
        } else if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_UNBONDING + account.account_address + IOV_UNBONDING_TAIL
        } else if (mChainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_UNBONDING + account.account_address + CERTIK_UNBONDING_TAIL
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_UNBONDING + account.account_address + SENTINEL_UNBONDING_TAIL
        }
        else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_UNBONDING + account.account_address + KAVA_TEST_UNBONDING_TAIL
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_UNBONDING + account.account_address + IOV_TEST_UNBONDING_TAIL
        } else if (mChainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_UNBONDING + account.account_address + CERTIK_TEST_UNBONDING_TAIL
        }
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                    let unbondinginfos = responseData.object(forKey: "result") as? Array<NSDictionary>,
                    unbondinginfos.count > 0  else {
                        _ = BaseData.instance.deleteUnbonding(account: account)
                        self.onFetchFinished()
                        return
                }
                BaseData.instance.updateUnbondings(self.mAccount.account_id, WUtils.getUnbondingwithUnbondingInfo(account, unbondinginfos, self.mChainType))
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchUnbondingInfo ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchEachReward(_ accountAddr: String, _ validatorAddr:String) {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_REWARD_FROM_VAL + accountAddr + KAVA_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (mChainType == ChainType.BAND_MAIN) {
            url = BAND_REWARD_FROM_VAL + accountAddr + BAND_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = SECRET_REWARD_FROM_VAL + accountAddr + SECRET_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_REWARD_FROM_VAL + accountAddr + IOV_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (mChainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_REWARD_FROM_VAL + accountAddr + CERTIK_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_REWARD_FROM_VAL + accountAddr + SENTINEL_REWARD_FROM_VAL_TAIL + validatorAddr
        }
        else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_REWARD_FROM_VAL + accountAddr + KAVA_TEST_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_REWARD_FROM_VAL + accountAddr + IOV_TEST_REWARD_FROM_VAL_TAIL + validatorAddr
        } else if (mChainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_REWARD_FROM_VAL + accountAddr + CERTIK_TEST_REWARD_FROM_VAL_TAIL + validatorAddr
        }
//        print("url ", url)
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                    let rawRewards = responseData.object(forKey: "result") as? Array<NSDictionary> else {
                        self.onFetchFinished()
                        return;
                }
                let reward = Reward.init()
                reward.reward_v_address = validatorAddr
                for rawReward in rawRewards {
                    reward.reward_amount.append(Coin(rawReward as! [String : Any]))
                }
                self.mRewardList.append(reward)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchEachReward ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchMintParam() {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_MINT_PARAM
        } else if (mChainType == ChainType.BAND_MAIN) {
            url = BAND_MINT_PARAM
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = SECRET_MINT_PARAM
        } else if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_MINT_PARAM
        } else if (mChainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_MINT_PARAM
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_MINT_PARAM
        }
        else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_MINT_PARAM
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_MINT_PARAM
        } else if (mChainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_MINT_PARAM
        }
        BaseData.instance.mMintParam = nil
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary else {
                    self.onFetchFinished()
                    return;
                }
                BaseData.instance.mMintParam = MintParam.init(responseData).result
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchMintParam ", error) }
            }
            self.onFetchFinished()
        }
        
    }
    
    func onFetchInflation() {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_INFLATION
        } else if (mChainType == ChainType.BAND_MAIN) {
            url = BAND_INFLATION
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = SECRET_INFLATION
        } else if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_INFLATION
        } else if (mChainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_INFLATION
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_INFLATION
        }
        else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_INFLATION
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_INFLATION
        } else if (mChainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_INFLATION
        }
        BaseData.instance.mInflation = nil
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                    let inflation = responseData.object(forKey: "result") as? String else {
                        self.onFetchFinished()
                        return;
                }
                BaseData.instance.mInflation = inflation.replacingOccurrences(of: "\"", with: "")
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchInflation ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchProvision() {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_PROVISIONS
        } else if (mChainType == ChainType.BAND_MAIN) {
            url = BAND_PROVISIONS
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = SECRET_PROVISIONS
        } else if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_PROVISIONS
        } else if (mChainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_PROVISIONS
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_PROVISIONS
        }
        else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_PROVISIONS
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_PROVISIONS
        } else if (mChainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_PROVISIONS
        }
        BaseData.instance.mProvision = nil
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                    let provisions = responseData.object(forKey: "result") as? String else {
                        self.onFetchFinished()
                        return;
                }
                BaseData.instance.mProvision = provisions.replacingOccurrences(of: "\"", with: "")
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchProvision ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchStakingPool() {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_STAKING_POOL
        } else if (mChainType == ChainType.BAND_MAIN) {
            url = BAND_STAKING_POOL
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = SECRET_STAKING_POOL
        } else if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_STAKING_POOL
        } else if (mChainType == ChainType.CERTIK_MAIN) {
            url = CERTIK_STAKING_POOL
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = SENTINEL_STAKING_POOL
        }
        else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_STAKING_POOL
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_STAKING_POOL
        } else if (mChainType == ChainType.CERTIK_TEST) {
            url = CERTIK_TEST_STAKING_POOL
        }
        BaseData.instance.mStakingPool = nil
        
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let responseData = res as? NSDictionary,
                      let stakingPool = responseData.object(forKey: "result") as? NSDictionary else {
                    self.onFetchFinished()
                    return;
                }
                if let height = responseData.object(forKey: "height") as? Int {
                    BaseData.instance.mHeight = height
                }
                if let heightS = responseData.object(forKey: "height") as? String, let height = Int(heightS) {
                    BaseData.instance.mHeight = height
                }
                BaseData.instance.mStakingPool = stakingPool
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchStakingPool ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchBnbTokens() {
        var url = ""
        if (mChainType == ChainType.BINANCE_MAIN ) {
            url = BNB_URL_TOKENS
        } else if (mChainType == ChainType.BINANCE_TEST) {
            url = BNB_TEST_URL_TOKENS
        }
        let request = Alamofire.request(url, method: .get, parameters: ["limit":"3000"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if let tokens = res as? Array<NSDictionary> {
                    for token in tokens {
                        let bnbToken = BnbToken(token as! [String : Any])
                        bnbToken.type = BNB_TOKEN_TYPE_BEP2
                        BaseData.instance.mBnbTokenList.append(bnbToken)
                    }
                }
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchBnbTokens ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchBnbMiniTokens() {
        var url = ""
        if (mChainType == ChainType.BINANCE_MAIN ) {
            url = BNB_URL_MINI_TOKENS
        } else if (mChainType == ChainType.BINANCE_TEST) {
            url = BNB_TEST_URL_MINI_TOKENS
        }
        let request = Alamofire.request(url, method: .get, parameters: ["limit":"3000"], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if let tokens = res as? Array<NSDictionary> {
                    for token in tokens {
                        let bnbToken = BnbToken(token as! [String : Any])
                        bnbToken.type = BNB_TOKEN_TYPE_MINI
                        BaseData.instance.mBnbTokenList.append(bnbToken)
                    }
                }
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchBnbTokens ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchPriceTic(_ showMsg:Bool) {
        var url: String?
        var parameters: Parameters?
        if (mChainType == ChainType.COSMOS_MAIN || mChainType == ChainType.COSMOS_TEST) {
            if (BaseData.instance.getMarket() == 0) {
                url = CGC_PRICE_TIC + "cosmos"
                parameters = [:]
            } else {
                url = CMC_PRICE_TIC + "3794"
                parameters = ["convert":BaseData.instance.getCurrencyString()]
            }
        } else if (mChainType == ChainType.IRIS_MAIN || mChainType == ChainType.IRIS_TEST) {
            if (BaseData.instance.getMarket() == 0) {
                url = CGC_PRICE_TIC + "iris-network"
                parameters = [:]
            } else {
                url = CMC_PRICE_TIC + "3874"
                parameters = ["convert":BaseData.instance.getCurrencyString()]
            }
        } else if (mChainType == ChainType.BINANCE_MAIN || mChainType == ChainType.BINANCE_TEST) {
            if (BaseData.instance.getMarket() == 0) {
                url = CGC_PRICE_TIC + "binancecoin"
                parameters = [:]
            } else {
                url = CMC_PRICE_TIC + "1839"
                parameters = ["convert":BaseData.instance.getCurrencyString()]
            }
        } else if (mChainType == ChainType.KAVA_MAIN || mChainType == ChainType.KAVA_TEST) {
            if (BaseData.instance.getMarket() == 0) {
                url = CGC_PRICE_TIC + "kava"
                parameters = [:]
            } else {
                url = CMC_PRICE_TIC + "4846"
                parameters = ["convert":BaseData.instance.getCurrencyString()]
            }
            
        } else if (mChainType == ChainType.BAND_MAIN) {
            url = CGC_PRICE_TIC + "band-protocol"
            parameters = [:]
            
        } else if (mChainType == ChainType.IOV_MAIN || mChainType == ChainType.IOV_TEST) {
            url = CGC_PRICE_TIC + "starname"
            parameters = [:]
            
        } else if (mChainType == ChainType.SECRET_MAIN) {
            url = CGC_PRICE_TIC + "secret"
            parameters = [:]
            
        } else if (mChainType == ChainType.OKEX_MAIN || mChainType == ChainType.OKEX_TEST) {
            url = CGC_PRICE_TIC + "okexchain"
            parameters = [:]
            
        } else if (mChainType == ChainType.CERTIK_MAIN || mChainType == ChainType.CERTIK_TEST) {
            url = CGC_PRICE_TIC + "certik"
            parameters = [:]
            
        } else if (mChainType == ChainType.AKASH_MAIN) {
            url = CGC_PRICE_TIC + "akash-network"
            parameters = [:]
            
        } else if (mChainType == ChainType.SENTINEL_MAIN) {
            url = CGC_PRICE_TIC + "sentinel-group"
            parameters = [:]
            
        } else {
            BaseData.instance.setPriceTicCgc(nil)
            return
        }
        let request = Alamofire.request(url!, method: .get,  parameters: parameters, encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                if let priceTic = res as? NSDictionary {
                    if (BaseData.instance.getMarket() == 0) {
                        BaseData.instance.setPriceTicCgc(priceTic)
                    } else {
                        BaseData.instance.setPriceTicCmc(priceTic)
                    }
                    self.mPriceTic = priceTic
                    if(showMsg) { self.onShowToast(NSLocalizedString("currency_fetch_success", comment: "")) }
                }
                
            case .failure(let error):
                if (showMsg) { self.onShowToast(NSLocalizedString("currency_fetch_failed", comment: "")) }
                if (SHOW_LOG) { print("onFetchPriceTic ", error) }
            }
            NotificationCenter.default.post(name: Notification.Name("onPriceFetchDone"), object: nil, userInfo: nil)
        }
    }
    
    
    
    func onFetchPriceFeedParam() {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_PRICE_FEED_PARAM
        } else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_PRICE_FEED_PARAM
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchPriceFeedParam res ", res)
                guard let responseData = res as? NSDictionary,
                    let _ = responseData.object(forKey: "height") as? String else {
                    self.onFetchFinished()
                    return
                }
                let priceParam = KavaPriceFeedParam.init(responseData)
                BaseData.instance.mKavaPrice.removeAll()
                self.mFetchCnt = self.mFetchCnt + (priceParam.result.markets.count / 2)
                for market in priceParam.result.markets {
                    if (market.market_id.contains(":30")) {
                        self.onFetchPriceFeedPrice(market.market_id)
                    }
                }
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchPriceFeedParam ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchPriceFeedPrice(_ market:String) {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_PRICE_FEED_PRICE + market
        } else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_PRICE_FEED_PRICE + market
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
//                print("onFetchPriceFeedPrice ", res)
                guard let responseData = res as? NSDictionary,
                    let _ = responseData.object(forKey: "height") as? String else {
                    self.onFetchFinished()
                    return
                }
                let priceParam = KavaPriceFeedPrice.init(responseData)
                BaseData.instance.mKavaPrice[priceParam.result.market_id] = priceParam
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchKavaPrice ", market , " ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchIncentiveParam() {
        var url: String?
        if (mChainType == ChainType.KAVA_MAIN) {
            url = KAVA_INCENTIVE_PARAM
        } else if (mChainType == ChainType.KAVA_TEST) {
            url = KAVA_TEST_INCENTIVE_PARAM
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
//                    print("IncentiveParam ", res)
                    guard let responseData = res as? NSDictionary,
                        let _ = responseData.object(forKey: "height") as? String else {
                            self.onFetchFinished()
                            return
                    }
                    let kavaIncentiveParam = KavaIncentiveParam.init(responseData)
                    BaseData.instance.mIncentiveParam = kavaIncentiveParam.result
//                    print("mIncentiveParam ", BaseData.instance.mIncentiveParam)
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchIncentiveParam ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchOkAccountBalance(_ account: Account) {
        var url: String?
        if (mChainType == ChainType.OKEX_MAIN) {
            url = OKEX_ACCOUNT_BALANCE  + account.account_address
        } else if (mChainType == ChainType.OKEX_TEST) {
            url = OKEX_TEST_ACCOUNT_BALANCE  + account.account_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let okAccountBalancesInfo = res as? [String : Any] else {
                    _ = BaseData.instance.deleteBalance(account: account)
                    self.onFetchFinished()
                    return
                }
                let okAccountBalances = OkAccountToken.init(okAccountBalancesInfo)
                BaseData.instance.updateBalances(account.account_id, WUtils.getBalancesWithOkAccountInfo(account, okAccountBalances))
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchOkAccountBalance ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchOkStakingInfo(_ account: Account) {
        var url: String?
        if (mChainType == ChainType.OKEX_MAIN) {
            url = OKEX_STAKING  + account.account_address
        } else if (mChainType == ChainType.OKEX_TEST) {
            url = OKEX_TEST_STAKING  + account.account_address
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? NSDictionary else {
                    self.onFetchFinished()
                    return
                }
                BaseData.instance.mOkStaking = OkStaking.init(info)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchOkStakingInfo ", error) }
            }
            self.onFetchFinished()
        }
        
    }
    
    func onFetchOkUnbondingInfo(_ account: Account) {
        var url: String?
        if (mChainType == ChainType.OKEX_MAIN) {
            url = OKEX_UNBONDING  + account.account_address + OKEX_UNBONDING_TAIL
        } else if (mChainType == ChainType.OKEX_TEST) {
            url = OKEX_TEST_UNBONDING  + account.account_address + OKEX_TEST_UNBONDING_TAIL
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? NSDictionary else {
                    self.onFetchFinished()
                    return
                }
                BaseData.instance.mOkUnbonding = OkUnbonding.init(info)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchOkWithdraw ", error) }
            }
            self.onFetchFinished()
        }
        
    }
    
    func onFetchOkTokenList() {
        var url: String?
        if (mChainType == ChainType.OKEX_MAIN) {
            url = OKEX_TOKEN_LIST
        } else if (mChainType == ChainType.OKEX_TEST) {
            url = OKEX_TEST_TOKEN_LIST
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let tokenList = res as? NSDictionary else {
                    self.onFetchFinished()
                    return
                }
                BaseData.instance.mOkTokenList = OkTokenList.init(tokenList)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchOkTokenList ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchOkDexTicker() {
        var url: String?
        if (mChainType == ChainType.OKEX_MAIN) {
            url = OKEX_TICKER_LIST
        } else if (mChainType == ChainType.OKEX_TEST) {
            url = OKEX_TEST_TICKER_LIST
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let tickerList = res as? NSDictionary else {
                    self.onFetchFinished()
                    return
                }
                BaseData.instance.mOkTickerList = OkTickerList.init(tickerList)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchOkDexTicker ", error) }
            }
            self.onFetchFinished()
        }
        
    }
    
    func onFetchStarNameFees() {
        var url: String?
        if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_STARNAME_FEE
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_STARNAME_FEE
        }
        let request = Alamofire.request(url!, method: .post, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    self.onFetchFinished()
                    return
                }
                BaseData.instance.mStarNameFee = IovStarNameFees.init(info).result.fees
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchStarNameFees ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchStarNameConfig() {
        var url: String?
        if (mChainType == ChainType.IOV_MAIN) {
            url = IOV_STARNAME_CONFIG
        } else if (mChainType == ChainType.IOV_TEST) {
            url = IOV_TEST_STARNAME_CONFIG
        }
        let request = Alamofire.request(url!, method: .post, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    self.onFetchFinished()
                    return
                }
                BaseData.instance.mStarNameConfig = IovStarNameConfig.init(info).result.configuration
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchStarNameConfig ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    func onFetchBandOracleStatus() {
        let url = BAND_ORACLE_STATUS
        let request = Alamofire.request(url, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
            case .success(let res):
                guard let info = res as? [String : Any] else {
                    self.onFetchFinished()
                    return
                }
                BaseData.instance.mBandOracleStatus = BandOracleStatus.init(info)
                
            case .failure(let error):
                if (SHOW_LOG) { print("onFetchBandOracleStatus ", error) }
            }
            self.onFetchFinished()
        }
    }
    
    
    //gRPC
    func onFetchgRPCNodeInfo() {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Base_Tendermint_V1beta1_GetNodeInfoRequest()
            
            do {
                let response = try Cosmos_Base_Tendermint_V1beta1_ServiceClient(channel: channel).getNodeInfo(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                BaseData.instance.mNodeInfo_gRPC = response.defaultNodeInfo
            } catch {
                print("onFetchgRPCNodeInfo failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCAuth(_ address: String) {
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }

            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }

            let req = Cosmos_Auth_V1beta1_QueryAccountRequest.with {
                $0.address = address
            }
            do {
                let response = try Cosmos_Auth_V1beta1_QueryClient(channel: channel).account(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                BaseData.instance.mAccount_gRPC = response.account

            } catch {
                print("onFetchgRPCAuth failed: \(error)")
            }

            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCBondedValidators(_ offset: Int) {
//        print("onFetchgRPCBondedValidators")
        DispatchQueue.global().async {
            
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let page = Cosmos_Base_Query_V1beta1_PageRequest.with {
                $0.limit = 125
            }
            let req = Cosmos_Staking_V1beta1_QueryValidatorsRequest.with {
                $0.pagination = page
                $0.status = "BOND_STATUS_BONDED"
            }
            do {
                let response = try Cosmos_Staking_V1beta1_QueryClient(channel: channel).validators(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchgRPCBondedValidators: \(response.validators.count)")
                response.validators.forEach { validator in
                    BaseData.instance.mBondedValidators_gRPC.append(validator)
                }
            } catch {
                print("onFetchgRPCBondedValidators failed: \(error)")
            }
            
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCUnbondedValidators(_ offset:Int) {
//        print("onFetchgRPCUnbondedValidators")
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let page = Cosmos_Base_Query_V1beta1_PageRequest.with {
                $0.limit = 125
            }
            let req = Cosmos_Staking_V1beta1_QueryValidatorsRequest.with {
                $0.pagination = page
                $0.status = "BOND_STATUS_UNBONDED"
            }
            do {
                let response = try Cosmos_Staking_V1beta1_QueryClient(channel: channel).validators(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchgRPCUnbondedValidators: \(response.validators.count)")
                response.validators.forEach { validator in
                    BaseData.instance.mUnbondValidators_gRPC.append(validator)
                }
            } catch {
                print("onFetchgRPCUnbondedValidators failed: \(error)")
            }
            
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCUnbondingValidators(_ offset:Int) {
//        print("onFetchgRPCUnbondingValidators")
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let page = Cosmos_Base_Query_V1beta1_PageRequest.with {
                $0.limit = 125
            }
            let req = Cosmos_Staking_V1beta1_QueryValidatorsRequest.with {
                $0.pagination = page
                $0.status = "BOND_STATUS_UNBONDING"
            }
            do {
                let response = try Cosmos_Staking_V1beta1_QueryClient(channel: channel).validators(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchgRPCUnbondingValidators: \(response.validators.count)")
                response.validators.forEach { validator in
                    BaseData.instance.mUnbondValidators_gRPC.append(validator)
                }
            } catch {
                print("onFetchgRPCUnbondingValidators failed: \(error)")
            }
            
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCBalance(_ address: String, _ offset:Int) {
//        print("onFetchgRPCDelegations")
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Bank_V1beta1_QueryAllBalancesRequest.with {
                $0.address = address
            }
            do {
                let response = try Cosmos_Bank_V1beta1_QueryClient(channel: channel).allBalances(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchgRPCBalance: \(response.balances)")
                response.balances.forEach { balance in
                    BaseData.instance.mMyBalances_gRPC.append(Coin.init(balance.denom, balance.amount))
                }
                if (BaseData.instance.mMyBalances_gRPC.count <= 0) {
                    BaseData.instance.mMyBalances_gRPC.append(Coin.init(WUtils.getMainDenom(self.mChainType), "0"))
                }
                
            } catch {
                print("onFetchgRPCBalance failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCDelegations(_ address: String, _ offset:Int) {
//        print("onFetchgRPCDelegations")
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Staking_V1beta1_QueryDelegatorDelegationsRequest.with {
                $0.delegatorAddr = address
            }
            do {
                let response = try Cosmos_Staking_V1beta1_QueryClient(channel: channel).delegatorDelegations(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchgRPCDelegations: \(response.delegationResponses.count)")
                response.delegationResponses.forEach { delegationResponse in
                    BaseData.instance.mMyDelegations_gRPC.append(delegationResponse)
                }
            } catch {
                print("onFetchgRPCDelegations failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCUndelegations(_ address: String, _ offset:Int) {
//        print("onFetchgRPCUndelegations")
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Staking_V1beta1_QueryDelegatorUnbondingDelegationsRequest.with {
                $0.delegatorAddr = address
            }
            do {
                let response = try Cosmos_Staking_V1beta1_QueryClient(channel: channel).delegatorUnbondingDelegations(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchgRPCUndelegations: \(response.unbondingResponses.count)")
                response.unbondingResponses.forEach { unbondingResponse in
                    BaseData.instance.mMyUnbondings_gRPC.append(unbondingResponse)
                }
            } catch {
                print("onFetchgRPCUndelegations failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCRewards(_ address: String, _ offset:Int) {
//        print("onFetchgRPCRewards")
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Distribution_V1beta1_QueryDelegationTotalRewardsRequest.with {
                $0.delegatorAddress = address
            }
            do {
                let response = try Cosmos_Distribution_V1beta1_QueryClient(channel: channel).delegationTotalRewards(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
//            print("onFetchgRPCRewards: \(response.rewards.count)")
                response.rewards.forEach { reward in
                    BaseData.instance.mMyReward_gRPC.append(reward)
                }
            } catch {
                print("onFetchgRPCRewards failed: \(error)")
            }
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCMintParam() {
//        print("onFetchgRPCMintParam")
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Mint_V1beta1_QueryParamsRequest()
            do {
                let response = try Cosmos_Mint_V1beta1_QueryClient(channel: channel).params(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchgRPCMintParam: \(response.params)")
                BaseData.instance.mMintParam_gRPC = response.params
            } catch {
                print("onFetchgRPCMintParam failed: \(error)")
            }
            
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCInflation() {
//        print("onFetchgRPCInflation")
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Mint_V1beta1_QueryInflationRequest()
            do {
                let response = try Cosmos_Mint_V1beta1_QueryClient(channel: channel).inflation(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                BaseData.instance.mInflation_gRPC = NSDecimalNumber.init(string: String(data: response.inflation, encoding: .utf8)).multiplying(byPowerOf10: -18)
//                print("onFetchgRPCInflation:  ", BaseData.instance.mInflation_gRPC)
            } catch {
                print("onFetchgRPCInflation failed: \(error)")
            }
            
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCProvision() {
//        print("onFetchgRPCProvision")
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Mint_V1beta1_QueryAnnualProvisionsRequest()
            do {
                let response = try Cosmos_Mint_V1beta1_QueryClient(channel: channel).annualProvisions(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
                BaseData.instance.mProvision_gRPC = NSDecimalNumber.init(string: String(data: response.annualProvisions, encoding: .utf8)).multiplying(byPowerOf10: -18)
    //            print("onFetchgRPCProvision:  ", BaseData.instance.mProvision_gRPC)
            } catch {
                print("onFetchgRPCProvision failed: \(error)")
            }
            
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCStakingPool() {
//        print("onFetchgRPCStakingPool")
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let req = Cosmos_Staking_V1beta1_QueryPoolRequest()
            do {
                let response = try Cosmos_Staking_V1beta1_QueryClient(channel: channel).pool(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchgRPCStakingPool: \(response.pool)")
                BaseData.instance.mStakingPool_gRPC = response.pool
            } catch {
                print("onFetchgRPCIrisMintParam failed: \(error)")
            }
            
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    func onFetchgRPCIrisMintParam() {
//        print("onFetchgRPCIrisMintParam")
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let req = Irishub_Mint_QueryParamsRequest()
            do {
                let response = try Irishub_Mint_QueryClient(channel: channel).params(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchgRPCIrisMintParam: \(response.params)")
                BaseData.instance.mIrisMintParam_gRPC = response.params
            } catch {
                print("onFetchgRPCIrisMintParam failed: \(error)")
            }
            
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    
    func onFetchgRPCIrisTokens() {
//        print("onFetchgRPCIrisTokens")
        DispatchQueue.global().async {
            let group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
            defer { try! group.syncShutdownGracefully() }
            
            let channel = BaseNetWork.getConnection(self.mChainType, group)!
            defer { try! channel.close().wait() }
            
            let page = Cosmos_Base_Query_V1beta1_PageRequest.with {
                $0.limit = 500
            }
            let req = Irismod_Token_QueryTokensRequest.with {
                $0.pagination = page
            }
            do {
                let response = try Irismod_Token_QueryClient(channel: channel).tokens(req, callOptions: BaseNetWork.getCallOptions()).response.wait()
//                print("onFetchgRPCIrisTokens: \(response.tokens.count)")
                response.tokens.forEach { token in
                    BaseData.instance.mIrisTokens_gRPC.append(try! Irismod_Token_Token.init(serializedData: token.value))
                }
            } catch {
                print("onFetchgRPCIrisTokens failed: \(error)")
            }
            
            DispatchQueue.main.async(execute: {
                self.onFetchFinished()
            });
        }
    }
    
    
    
    func onShowToast(_ text:String) {
        var style = ToastStyle()
        style.backgroundColor = UIColor.gray
        self.view.makeToast(text, duration: 2.0, position: .bottom, style: style)
    }
    
    public func showWaittingAlert() {
        waitAlert = UIAlertController(title: "", message: "\n\n\n\n", preferredStyle: .alert)
        let image = LoadingImageView(frame: CGRect(x: 0, y: 0, width: 58, height: 58))
        waitAlert!.view.addSubview(image)
        image.translatesAutoresizingMaskIntoConstraints = false
        waitAlert!.view.addConstraint(NSLayoutConstraint(item: image, attribute: .centerX, relatedBy: .equal, toItem: waitAlert!.view, attribute: .centerX, multiplier: 1, constant: 0))
        waitAlert!.view.addConstraint(NSLayoutConstraint(item: image, attribute: .centerY, relatedBy: .equal, toItem: waitAlert!.view, attribute: .centerY, multiplier: 1, constant: 0))
        waitAlert!.view.addConstraint(NSLayoutConstraint(item: image, attribute: .width, relatedBy: .equal, toItem: nil, attribute: .notAnAttribute, multiplier: 1.0, constant: 58.0))
        waitAlert!.view.addConstraint(NSLayoutConstraint(item: image, attribute: .height, relatedBy: .equal, toItem: nil, attribute: .notAnAttribute, multiplier: 1.0, constant: 58.0))
        WUtils.clearBackgroundColor(of: waitAlert!.view)
        self.present(waitAlert!, animated: true, completion: nil)
        image.onStartAnimation()
        
    }
    
    public func showKavaTestWarn() {
        let warnAlert = UIAlertController(title: NSLocalizedString("testnet_warn_title", comment: ""), message: "", preferredStyle: .alert)
        let paragraphStyle = NSMutableParagraphStyle()
        paragraphStyle.alignment = NSTextAlignment.left
        let messageText = NSMutableAttributedString(
            string: NSLocalizedString("testnet_warn_msg", comment: ""),
            attributes: [
                NSAttributedString.Key.paragraphStyle: paragraphStyle,
                NSAttributedString.Key.font: UIFont.preferredFont(forTextStyle: UIFont.TextStyle.caption1)
            ]
        )
        warnAlert.setValue(messageText, forKey: "attributedMessage")
        warnAlert.addAction(UIAlertAction(title: NSLocalizedString("str_no_more_3day", comment: ""), style: .destructive, handler: { _ in
            BaseData.instance.setKavaWarn()
        }))
        warnAlert.addAction(UIAlertAction(title: NSLocalizedString("confirm", comment: ""), style: .default, handler: nil))
        self.present(warnAlert, animated: true, completion: nil)
    }
    
    public func hideWaittingAlert() {
        if (waitAlert != nil) {
            waitAlert?.dismiss(animated: true, completion: nil)
        }
    }
    
    func accountSelected(_ id: Int) {
        if (id != self.mAccount.account_id) {
            BaseData.instance.setRecentAccountId(Int64(id))
            BaseData.instance.setLastTab(self.selectedIndex)

            let mainTabVC = UIStoryboard(name: "MainStoryboard", bundle: nil).instantiateViewController(withIdentifier: "MainTabViewController") as! MainTabViewController
            let appDelegate = UIApplication.shared.delegate as! AppDelegate            
            appDelegate.window?.rootViewController = mainTabVC
            self.present(mainTabVC, animated: true, completion: nil)
        }
    }
    
    var targetChain:ChainType?
    func addAccount(_ chain: ChainType) {
        targetChain = chain
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(610), execute: {
            let popupContent = AddViewController.create()
            let cardPopup = SBCardPopupViewController(contentViewController: popupContent)
            cardPopup.resultDelegate = self
            cardPopup.show(onViewController: self)
        })
    }
    
}
