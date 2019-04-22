//
//  StepGenTxViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 08/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class StepGenTxViewController: UIPageViewController, UIPageViewControllerDelegate, UIPageViewControllerDataSource, UIScrollViewDelegate {
    
    fileprivate var currentIndex = 0
    var disableBounce = false
    
    var topVC: StakingViewController!
    var mType: String?
    
    var mAccount: Account?
    var mBalances = Array<Balance>()
    var mBondingList = Array<Bonding>()
    var mUnbondingList = Array<Unbonding>()
    var mReward = Reward.init()
    var mRewardAddress: String?
    
    var mTargetValidator: Validator?
    var mToDelegateAmount: Coin?
    var mToUndelegateAmount:Coin?
    
    var mToSendRecipientAddress:String?
    var mToSendAmount = Array<Coin>()
    
    var mMemo: String?
    var mFee: Fee?
    
    lazy var orderedViewControllers: [UIViewController] = {
        if (mType == COSMOS_MSG_TYPE_DELEGATE) {
            return [self.newVc(viewController: "StepDelegateAmountViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    self.newVc(viewController: "StepFeeViewController"),
                    self.newVc(viewController: "StepDelegateCheckViewController")]
            
        } else if (mType == COSMOS_MSG_TYPE_UNDELEGATE2) {
            return [self.newVc(viewController: "StepUndelegateAmountViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    self.newVc(viewController: "StepFeeViewController"),
                    self.newVc(viewController: "StepUndelegateCheckViewController")]
            
        }else if (mType == COSMOS_MSG_TYPE_TRANSFER2) {
            return [self.newVc(viewController: "StepSendAddressViewController"),
                    self.newVc(viewController: "StepSendAmountViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    self.newVc(viewController: "StepFeeViewController"),
                    self.newVc(viewController: "StepSendCheckViewController")]
            
        } else {
            return [self.newVc(viewController: "StepRewardViewController"),
                    self.newVc(viewController: "StepMemoViewController"),
                    self.newVc(viewController: "StepFeeViewController"),
                    self.newVc(viewController: "StepRewardCheckViewController")]
            
        }
        
    }()
    
    required init?(coder aDecoder: NSCoder) {
        super.init(transitionStyle: .scroll, navigationOrientation: .horizontal, options: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
//        print("StepGenTxViewController")
        
        mAccount        = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        mBalances       = BaseData.instance.selectBalanceById(accountId: mAccount!.account_id)
        mBondingList    = BaseData.instance.selectBondingById(accountId: mAccount!.account_id)
        mUnbondingList  = BaseData.instance.selectUnbondingById(accountId: mAccount!.account_id)
            
        self.dataSource = self
        self.delegate = self
        if let firstViewController = orderedViewControllers.first {
            setViewControllers([firstViewController], direction: .forward, animated: true, completion: nil)
        }
        
        for view in self.view.subviews {
            if let subView = view as? UIScrollView {
                subView.delegate = self
                subView.isScrollEnabled = false
                subView.bouncesZoom = false

            }
        }
        disableBounce = true
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
    }
    
    func newVc(viewController: String) ->UIViewController {
        return UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: viewController)
    }
    
    func onBeforePage() {
        disableBounce = false
        if(currentIndex == 0) {
            self.navigationController?.popViewController(animated: true)
        } else {
            setViewControllers([orderedViewControllers[currentIndex - 1]], direction: .reverse, animated: true, completion: { (finished) -> Void in
                self.currentIndex = self.currentIndex - 1
                let value:[String: Int] = ["step": self.currentIndex ]
                NotificationCenter.default.post(name: Notification.Name("stepChanged"), object: nil, userInfo: value)
                let currentVC = self.orderedViewControllers[self.currentIndex] as! BaseViewController
                currentVC.enableUserInteraction()
                self.disableBounce = true
            })
        }
        
    }
    
    func onNextPage() {
        disableBounce = false
        if((currentIndex <= 3 && mType == COSMOS_MSG_TYPE_TRANSFER2) || currentIndex <= 2) {
            setViewControllers([orderedViewControllers[currentIndex + 1]], direction: .forward, animated: true, completion: { (finished) -> Void in
                self.currentIndex = self.currentIndex + 1
                let value:[String: Int] = ["step": self.currentIndex ]
                NotificationCenter.default.post(name: Notification.Name("stepChanged"), object: nil, userInfo: value)
                let currentVC = self.orderedViewControllers[self.currentIndex] as! BaseViewController
                currentVC.enableUserInteraction()
                self.disableBounce = true
            })
        }
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerBefore viewController: UIViewController) -> UIViewController? {
        return nil
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerAfter viewController: UIViewController) -> UIViewController? {
        return nil
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, didFinishAnimating finished: Bool, previousViewControllers: [UIViewController], transitionCompleted completed: Bool) {
        if(!completed) {
//            print("not completed")
        } else {
            if let currentViewController = pageViewController.viewControllers?.first,
                let index = orderedViewControllers.index(of: currentViewController) {
                currentIndex = index
            }
//            print("currentIndex ", currentIndex)
            let value:[String: Int] = ["step": currentIndex]
            NotificationCenter.default.post(name: Notification.Name("stepChanged"), object: nil, userInfo: value)
        }
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        if(disableBounce) {
            scrollView.contentOffset = CGPoint(x: scrollView.bounds.size.width, y: 0);
        }
    }
    
    func scrollViewWillEndDragging(_ scrollView: UIScrollView, withVelocity velocity: CGPoint, targetContentOffset: UnsafeMutablePointer<CGPoint>) {
        if(disableBounce) {
            targetContentOffset.pointee = CGPoint(x: scrollView.bounds.size.width, y: 0);
        }
    }
    
}
