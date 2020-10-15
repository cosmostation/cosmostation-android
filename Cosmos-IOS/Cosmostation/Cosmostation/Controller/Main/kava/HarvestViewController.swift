//
//  HarvestListViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit

class HarvestViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var harvestTableView: UITableView!
    var mainTabVC: MainTabViewController!
    var refresher: UIRefreshControl!
    
    var havestParam: KavaHavestParam?
    var myLPs = Array<KavaHavestParam.DistributionSchedule>()
    var otherLPs = Array<KavaHavestParam.DistributionSchedule>()
    var havestDeposits = Array<KavaHavestDeposit.HavestDeposit>()
    var havestRewards = Array<KavaHavestReward.HavestReward>()
    var havestStakeRewards = Array<KavaHavestReward.HavestReward>()
    var havestLPRewards = Array<KavaHavestReward.HavestReward>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.havestParam = BaseData.instance.mHavestParam
        self.havestDeposits = BaseData.instance.mHavestDeposits
        self.havestRewards = BaseData.instance.mHavestRewards
        self.onSortHarvest()
        
        self.harvestTableView.delegate = self
        self.harvestTableView.dataSource = self
        self.harvestTableView.register(UINib(nibName: "HarvestListStakeCell", bundle: nil), forCellReuseIdentifier: "HarvestListStakeCell")
        self.harvestTableView.register(UINib(nibName: "HarvestListRewardCell", bundle: nil), forCellReuseIdentifier: "HarvestListRewardCell")
        self.harvestTableView.register(UINib(nibName: "HarvestListMyCell", bundle: nil), forCellReuseIdentifier: "HarvestListMyCell")
        self.harvestTableView.register(UINib(nibName: "HarvestListAllCell", bundle: nil), forCellReuseIdentifier: "HarvestListAllCell")
        self.harvestTableView.register(HarvestListHeader.self, forHeaderFooterViewReuseIdentifier: "sectionHeader")
        
        self.harvestTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.harvestTableView.rowHeight = UITableView.automaticDimension
        self.harvestTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onRequestFetch), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.harvestTableView.addSubview(refresher)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.mainTabVC = ((self.parent)?.parent)?.parent as? MainTabViewController
    }
    
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(self.onFetchDone(_:)), name: Notification.Name("onFetchDone"), object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("onFetchDone"), object: nil)
    }
    
    @objc func onRequestFetch() {
        if (!mainTabVC.onFetchAccountData()) {
            self.refresher.endRefreshing()
        }
    }
    
    @objc func onFetchDone(_ notification: NSNotification) {
        self.havestParam = BaseData.instance.mHavestParam
        self.havestDeposits = BaseData.instance.mHavestDeposits
        self.havestRewards = BaseData.instance.mHavestRewards
        self.onSortHarvest()
        self.refresher.endRefreshing()
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            return 1
            
        } else if (section == 1) {
            if (havestLPRewards.count > 0) {
                return myLPs.count + 1
            }
            return myLPs.count

        } else if (section == 2){
            return otherLPs.count

        } else {
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if (indexPath.section == 0) {
            return onBindStake(tableView, indexPath.row)
            
        } else if (indexPath.section == 1) {
            if (havestLPRewards.count > 0 && indexPath.row == 0) {
                return onBindLPClaim(tableView, indexPath.row)
            }
            return onBindMyLP(tableView, indexPath.row)

        } else {
            return onBindOtherLP(tableView, indexPath.row)
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.section == 0) {
            return
            
        } else if (indexPath.section == 1) {
            if (havestLPRewards.count > 0 && indexPath.row == 0) {
                return
            }
            var myLPInfo: KavaHavestParam.DistributionSchedule?
            if (havestLPRewards.count > 0) { myLPInfo = self.myLPs[indexPath.row - 1]
            } else { myLPInfo = self.myLPs[indexPath.row] }
            let harvestDetailVC = HarvestDetailViewController(nibName: "HarvestDetailViewController", bundle: nil)
            harvestDetailVC.hidesBottomBarWhenPushed = true
            harvestDetailVC.mDepositDenom = myLPInfo?.deposit_denom
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(harvestDetailVC, animated: true)

        } else {
            let otherLP = self.otherLPs[indexPath.row]
            let harvestDetailVC = HarvestDetailViewController(nibName: "HarvestDetailViewController", bundle: nil)
            harvestDetailVC.hidesBottomBarWhenPushed = true
            harvestDetailVC.mDepositDenom = otherLP.deposit_denom
            self.navigationItem.title = ""
            self.navigationController?.pushViewController(harvestDetailVC, animated: true)
        }
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let view = tableView.dequeueReusableHeaderFooterView(withIdentifier: "sectionHeader") as! HarvestListHeader
        view.title.textColor = .white
        view.title.font = view.title.font.withSize(14)
        if (section == 0) {
            view.title.text = "HARD reward for KAVA delegators"
        } else {
            view.title.text = "Harvest Markets"
        }
        return view
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        if (section == 2) {
            return 0
        }
        return 30
    }
    
    func onBindStake(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:HarvestListStakeCell? = tableView.dequeueReusableCell(withIdentifier:"HarvestListStakeCell") as? HarvestListStakeCell
        cell?.harvestTitle.text = "KAVA STAKE"
        cell?.starTime.text = WUtils.txTimetoString(input: havestParam?.getKavaStakerSchedule()?.distribution_schedule.start)
        cell?.endTime.text = WUtils.txTimetoString(input: havestParam?.getKavaStakerSchedule()?.distribution_schedule.end)
        if let rewardRate = havestParam?.getKavaStakerSchedule()?.distribution_schedule.rewards_per_second {
            cell?.rewardForSecond.attributedText = WUtils.displayAmount2(rewardRate.amount, cell!.rewardForSecond.font, 6, 6)
        }
        
        var hardReward = NSDecimalNumber.zero
        for hReward in havestStakeRewards {
            hardReward = hardReward.adding(WUtils.plainStringToDecimal(hReward.amount.amount))
        }
        cell?.rewardAmount.attributedText = WUtils.displayAmount2(hardReward.stringValue, cell!.rewardAmount.font, 6, 6)
        
        let bondingList = BaseData.instance.selectBondingById(accountId: account!.account_id)
        let allValidators = BaseData.instance.mAllValidator
        let delegatedAmount = WUtils.deleagtedAmount(bondingList, allValidators, chainType!)
        cell?.stakeAmount.attributedText = WUtils.displayAmount2(delegatedAmount.stringValue, cell!.rewardAmount.font, 6, 6)
        
        cell?.actionClaim = {
            self.onClickClaim()
        }
        return cell!
    }
    
    func onBindLPClaim(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let cell:HarvestListRewardCell? = tableView.dequeueReusableCell(withIdentifier:"HarvestListRewardCell") as? HarvestListRewardCell
        var hardReward = NSDecimalNumber.zero
        for hReward in havestLPRewards {
            hardReward = hardReward.adding(WUtils.plainStringToDecimal(hReward.amount.amount))
        }
        cell?.rewardSumAmount.attributedText = WUtils.displayAmount2(hardReward.stringValue, cell!.rewardSumAmount.font, 6, 6)
        return cell!
    }
    
    func onBindMyLP(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        var myLPInfo: KavaHavestParam.DistributionSchedule?
        if (havestLPRewards.count > 0) {
            myLPInfo = self.myLPs[position - 1]
        } else {
            myLPInfo = self.myLPs[position]
        }
        let myDeposit = havestDeposits.filter { $0.amount.denom == myLPInfo?.deposit_denom }
        let myReward = havestRewards.filter { $0.deposit_denom == myLPInfo?.deposit_denom }
        
        let cell:HarvestListMyCell? = tableView.dequeueReusableCell(withIdentifier:"HarvestListMyCell") as? HarvestListMyCell
        let title = (myLPInfo!.deposit_denom == KAVA_MAIN_DENOM) ? "kava" : myLPInfo!.deposit_denom
        cell?.harvestTitle.text = title.uppercased() + " POOL"
        cell?.starTime.text = WUtils.txTimetoString(input: myLPInfo?.start)
        cell?.endTime.text = WUtils.txTimetoString(input: myLPInfo?.end)
        if let rewardRate = myLPInfo?.rewards_per_second {
            cell?.rewardForSecond.attributedText = WUtils.displayAmount2(rewardRate.amount, cell!.rewardForSecond.font, 6, 6)
        }
        if let depositCoin = myDeposit.first?.amount {
            WUtils.showCoinDp(depositCoin, cell!.depositedDenom, cell!.depositedAmount, chainType!)
        }
        if let rewardtCoin = myReward.first?.amount {
            WUtils.showCoinDp(rewardtCoin, cell!.rewardDenom, cell!.rewardAmount, chainType!)
        }
        let url = KAVA_HARVEST_MARKET_IMG_URL + "lp" + myLPInfo!.deposit_denom + ".png"
        cell?.harvestImg.af_setImage(withURL: URL(string: url)!)
        return cell!
    }
    
    func onBindOtherLP(_ tableView: UITableView, _ position:Int) -> UITableViewCell  {
        let otherLP = self.otherLPs[position]
        let cell:HarvestListAllCell? = tableView.dequeueReusableCell(withIdentifier:"HarvestListAllCell") as? HarvestListAllCell
        let title = (otherLP.deposit_denom == KAVA_MAIN_DENOM) ? "kava" : otherLP.deposit_denom
        cell?.harvestTitle.text = title.uppercased() + " POOL"
        cell?.starTime.text = WUtils.txTimetoString(input: otherLP.start)
        cell?.endTime.text = WUtils.txTimetoString(input: otherLP.end)
        cell?.rewardForSecond.attributedText = WUtils.displayAmount2(otherLP.rewards_per_second.amount, cell!.rewardForSecond.font, 6, 6)
        let url = KAVA_HARVEST_MARKET_IMG_URL + "lp" + otherLP.deposit_denom + ".png"
        cell?.harvestImg.af_setImage(withURL: URL(string: url)!)
        return cell!
    }
    
    
    
    func onClickClaim() {
        if (!onCommonCheck()) { return }
        var hardReward = NSDecimalNumber.zero
        for hReward in havestStakeRewards {
            hardReward = hardReward.adding(WUtils.plainStringToDecimal(hReward.amount.amount))
        }
        if (hardReward.compare(NSDecimalNumber.zero).rawValue <= 0) {
            self.onShowToast(NSLocalizedString("error_no_harvest_reward_to_claim", comment: ""))
            return
        }
        let txVC = UIStoryboard(name: "GenTx", bundle: nil).instantiateViewController(withIdentifier: "TransactionViewController") as! TransactionViewController
        txVC.mType = KAVA_MSG_TYPE_CLAIM_HAVEST
        txVC.mHarvestDepositDenom = havestParam?.getKavaStakerSchedule()?.distribution_schedule.deposit_denom       //ukava
        txVC.mHarvestDepositType = "stake"
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(txVC, animated: true)
    }
    
    
    func onCommonCheck() -> Bool {
        if (!account!.account_has_private) {
            self.onShowAddMenomicDialog()
            return false
        }
        if (!(havestParam?.getKavaStakerSchedule()?.distribution_schedule.active)!) {
            self.onShowToast(NSLocalizedString("error_circuit_breaker", comment: ""))
            return false
        }
        return true
    }
    
    func onSortHarvest() {
        self.havestStakeRewards = havestRewards.filter { $0.type == "stake" }
        self.havestLPRewards = havestRewards.filter { $0.type == "lp" }
        self.myLPs.removeAll()
        self.otherLPs.removeAll()
        if let liquidityProviderSchedules = havestParam?.result.liquidity_provider_schedules  {
            for schedule in liquidityProviderSchedules {
                if (!schedule.active) { continue }
                var has = false
                for havestDeposit in havestDeposits {
                    if (havestDeposit.amount.denom == schedule.deposit_denom) {
                        has = true
                    }
                }
                if (has) {
                    self.myLPs.append(schedule)
                } else {
                    self.otherLPs.append(schedule)
                }
            }
        }
    }
}
