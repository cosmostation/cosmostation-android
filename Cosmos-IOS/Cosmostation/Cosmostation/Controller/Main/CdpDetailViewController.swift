//
//  CdpDetailViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage

class CdpDetailViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    

    @IBOutlet weak var cdpDetailTableView: UITableView!
    @IBOutlet weak var createCdpBtn: UIButton!
    @IBOutlet weak var loadingImg: LoadingImageView!
    var refresher: UIRefreshControl!
    @IBOutlet weak var emptyConstraint: NSLayoutConstraint!
    @IBOutlet weak var owenConstraint: NSLayoutConstraint!
    
    var cDenom: String = ""
    var mMarketID: String = ""
    
//    var mCdpParam: CdpParam = CdpParam.init()
//    var cParam: CdpParam.CollateralParam = CdpParam.CollateralParam.init()
    var cParam: CdpParam.CollateralParam?
    var mMyCdps: CdpOwen?
    var mMyCdpDeposit: CdpDeposits?
    var mPrice: KavaTokenPrice = KavaTokenPrice.init()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        
        self.cdpDetailTableView.delegate = self
        self.cdpDetailTableView.dataSource = self
        self.cdpDetailTableView.register(UINib(nibName: "CdpDetailTopCell", bundle: nil), forCellReuseIdentifier: "CdpDetailTopCell")
        self.cdpDetailTableView.register(UINib(nibName: "CdpDetailMyTopCell", bundle: nil), forCellReuseIdentifier: "CdpDetailMyTopCell")
        self.cdpDetailTableView.register(UINib(nibName: "CdpDetailMyActionCell", bundle: nil), forCellReuseIdentifier: "CdpDetailMyActionCell")
        self.cdpDetailTableView.register(UINib(nibName: "CdpDetailAssetsCell", bundle: nil), forCellReuseIdentifier: "CdpDetailAssetsCell")
        self.cdpDetailTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.cdpDetailTableView.rowHeight = UITableView.automaticDimension
        self.cdpDetailTableView.estimatedRowHeight = UITableView.automaticDimension
        
        self.refresher = UIRefreshControl()
        self.refresher.addTarget(self, action: #selector(onFetchCdpData), for: .valueChanged)
        self.refresher.tintColor = UIColor.white
        self.cdpDetailTableView.addSubview(refresher)
        
        print("cDenom ", cDenom)
        print("mMarketID ", mMarketID)
        
        self.loadingImg.onStartAnimation()
        self.onFetchCdpData()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_cdp_detail", comment: "")
        self.navigationItem.title = NSLocalizedString("title_cdp_detail", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    

    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if ((mMyCdps) != nil) {
            return 3
        } else {
            return 2
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if ((mMyCdps) != nil) {
            if (indexPath.row == 0) {
                let cell:CdpDetailMyTopCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailMyTopCell") as? CdpDetailMyTopCell
                return cell!
                
            } else if (indexPath.row == 1) {
                let cell:CdpDetailMyActionCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailMyActionCell") as? CdpDetailMyActionCell
                return cell!
                
            } else {
                return self.onSetAssetsItems(tableView, indexPath)
                
            }
            
        } else {
            if (indexPath.row == 0) {
                return self.onSetDetailTopItems(tableView, indexPath)
                
            } else {
                return self.onSetAssetsItems(tableView, indexPath)
            }
        }
    }
    
    
    
    func onSetDetailTopItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:CdpDetailTopCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailTopCell") as? CdpDetailTopCell
        if (cParam != nil) {
            cell?.marketTitle.text = cParam!.getDpMarketId()
            cell?.minCollateralRate.attributedText = WUtils.displayPercent(cParam!.getDpLiquidationRatio(), font: cell!.minCollateralRate.font)
            cell?.stabilityFee.attributedText = WUtils.displayPercent(cParam!.getDpStabilityFee(), font: cell!.stabilityFee.font)
            cell?.liquidationPenalty.attributedText = WUtils.displayPercent(cParam!.getDpLiquidationPenalty(), font: cell!.liquidationPenalty.font)
            cell?.currentPrice.attributedText = WUtils.getDPRawDollor(currentPrice.stringValue, 4, cell!.currentPrice.font)
            let url = KAVA_CDP_MARKET_IMG_URL + cParam!.getMarketImgPath() + ".png"
            Alamofire.request(url, method: .get).responseImage { response  in
                guard let image = response.result.value else { return }
                cell?.marketImg.image = image
            }
            cell?.helpCollateralRate = {
                self.onShowSimpleHelp(NSLocalizedString("help_collateral_rate_title", comment: ""), NSLocalizedString("help_collateral_rate_msg", comment: ""))
            }
            cell?.helpStabilityFee = {
                self.onShowSimpleHelp(NSLocalizedString("help_stability_fee_title", comment: ""), NSLocalizedString("help_stability_fee_msg", comment: ""))
            }
            cell?.helpLiquidationPenalty = {
                self.onShowSimpleHelp(NSLocalizedString("help_liquidation_penalty_title", comment: ""), NSLocalizedString("help_liquidation_penalty_msg", comment: ""))
            }
        }
        return cell!
        
    }
    
    
    func onSetAssetsItems(_ tableView: UITableView, _ indexPath: IndexPath) -> UITableViewCell {
        let cell:CdpDetailAssetsCell? = tableView.dequeueReusableCell(withIdentifier:"CdpDetailAssetsCell") as? CdpDetailAssetsCell
        
        cell?.collateralDenom.text = cDenom.uppercased()
        cell?.collateralAmount.attributedText = WUtils.displayAmount2(cAvailable.stringValue, cell!.collateralAmount.font!, WUtils.getKavaCoinDecimal(cDenom), WUtils.getKavaCoinDecimal(cDenom))
        let collateralValue = cAvailable.multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal(cDenom)).multiplying(by: currentPrice, withBehavior: WUtils.handler2Down)
        cell?.collateralValue.attributedText = WUtils.getDPRawDollor(collateralValue.stringValue, 2, cell!.collateralValue.font)
        
        cell?.principalDenom.text = pDenom.uppercased()
        cell?.principalAmount.attributedText = WUtils.displayAmount2(pAvailable.stringValue, cell!.principalAmount.font!, WUtils.getKavaCoinDecimal(pDenom), WUtils.getKavaCoinDecimal(pDenom))
        let principalValue = pAvailable.multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal(pDenom))
        cell?.principalValue.attributedText = WUtils.getDPRawDollor(principalValue.stringValue, 2, cell!.principalValue.font)
        
        cell?.kavaAmount.attributedText = WUtils.displayAmount2(kAvailable.stringValue, cell!.kavaAmount.font!, WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM), WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM))
        let kavaValue = kAvailable.multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal(KAVA_MAIN_DENOM)).multiplying(by: BaseData.instance.getLastDollorPrice(), withBehavior: WUtils.handler2Down)
        cell?.kavaValue.attributedText = WUtils.getDPRawDollor(kavaValue.stringValue, 2, cell!.kavaValue.font)
        
        Alamofire.request(KAVA_COIN_IMG_URL + cDenom + ".png", method: .get).responseImage { response  in
            guard let image = response.result.value else { return }
            cell?.collateralImg.image = image
        }
        Alamofire.request(KAVA_COIN_IMG_URL + pDenom + ".png", method: .get).responseImage { response  in
            guard let image = response.result.value else { return }
            cell?.principalImg.image = image
        }
        return cell!
    }

    
    @IBAction func onClickCreateCdp(_ sender: UIButton) {
        
    }
    
    
    
    var mFetchCnt = 0
    @objc func onFetchCdpData() {
        if(self.mFetchCnt > 0)  {
            self.refresher.endRefreshing()
            return
        }
        self.mFetchCnt = 4
        onFetchCdpParam(account!)
        onFetchOwenCdp(account!, self.cDenom)
        onFetchCdpDeposit(account!, self.cDenom)
        onFetchKavaPrice(self.mMarketID)
    }
    
    
    func onFetchFinished() {
        self.mFetchCnt = self.mFetchCnt - 1
        if (mFetchCnt <= 0) {
            if ((cParam) == nil) {
                print("ERROR");
                return
            }
            pDenom = cParam!.getpDenom()
            cAvailable = account!.getTokenBalance(cDenom)
            pAvailable = account!.getTokenBalance(pDenom)
            kAvailable = account!.getTokenBalance(KAVA_MAIN_DENOM)
            currentPrice = NSDecimalNumber.init(string: mPrice.result.price)
//            print("cAvailable ", cAvailable)
//            print("pAvailable ", pAvailable)
//            print("kAvailable ", kAvailable)
            
            if ((mMyCdps) != nil) {
                emptyConstraint.priority = .defaultLow
                owenConstraint.priority = .defaultHigh
                createCdpBtn.isHidden = true
            } else {
                emptyConstraint.priority = .defaultHigh
                owenConstraint.priority = .defaultLow
                createCdpBtn.isHidden = false
            }
            self.cdpDetailTableView.reloadData()
            self.cdpDetailTableView.isHidden = false
            self.loadingImg.onStopAnimation()
            self.loadingImg.isHidden = true
            self.refresher.endRefreshing()
        }
    }
    
    var pDenom: String = ""
    var cAvailable: NSDecimalNumber = NSDecimalNumber.zero
    var pAvailable: NSDecimalNumber = NSDecimalNumber.zero
    var kAvailable: NSDecimalNumber = NSDecimalNumber.zero
    var currentPrice: NSDecimalNumber = NSDecimalNumber.zero
    
    func onShowSimpleHelp(_ title:String, _ msg:String) {
        let helpAlert = UIAlertController(title: title, message: msg, preferredStyle: .alert)
        let paragraphStyle = NSMutableParagraphStyle()
        paragraphStyle.alignment = NSTextAlignment.left
        let attributedMessage: NSMutableAttributedString = NSMutableAttributedString(
            string: msg,
            attributes: [
                NSAttributedString.Key.paragraphStyle: paragraphStyle,
                NSAttributedString.Key.font: UIFont.preferredFont(forTextStyle: UIFont.TextStyle.footnote)
            ]
        )
        helpAlert.setValue(attributedMessage, forKey: "attributedMessage")
        helpAlert.addAction(UIAlertAction(title: NSLocalizedString("close", comment: ""), style: .default, handler: { _ in
            self.dismiss(animated: true, completion: nil)
        }))
        self.present(helpAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            helpAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    
    
    func onFetchCdpParam(_ account:Account) {
        var url: String?
        if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = ""
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_CDP_PARAM
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary,
                        let _ = responseData.object(forKey: "height") as? String else {
                            self.onFetchFinished()
                            return
                    }
                    self.cParam = CdpParam.init(responseData).result.getcParam(self.cDenom)
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchCdpParam ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchOwenCdp(_ account:Account, _ denom:String) {
        var url: String?
        if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = ""
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_CDP_OWEN + account.account_address + "/" + denom
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary,
                        let _ = responseData.object(forKey: "height") as? String,
                        responseData.object(forKey: "result") != nil else {
                            self.onFetchFinished()
                            return
                    }
                    self.mMyCdps = CdpOwen.init(responseData)
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchOwenCdp ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchCdpDeposit(_ account:Account, _ denom:String) {
        var url: String?
        if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = ""
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_CDP_DEPOSIT + account.account_address + "/" + denom
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary,
                        let _ = responseData.object(forKey: "height") as? String,
                        responseData.object(forKey: "result") != nil else {
                            self.onFetchFinished()
                            return
                    }
                    self.mMyCdpDeposit = CdpDeposits.init(responseData)
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchCdpDeposit ", error) }
                }
            self.onFetchFinished()
        }
    }
    
    func onFetchKavaPrice(_ market:String) {
        var url: String?
        if (chainType == ChainType.SUPPORT_CHAIN_KAVA_MAIN) {
            url = ""
        } else if (chainType == ChainType.SUPPORT_CHAIN_KAVA_TEST) {
            url = KAVA_TEST_TOKEN_PRICE + market
        }
        let request = Alamofire.request(url!, method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:]);
        request.responseJSON { (response) in
            switch response.result {
                case .success(let res):
                    guard let responseData = res as? NSDictionary,
                        let _ = responseData.object(forKey: "height") as? String else {
                            self.onFetchFinished()
                            return
                    }
                    self.mPrice = KavaTokenPrice.init(responseData)
                    
                case .failure(let error):
                    if (SHOW_LOG) { print("onFetchKavaPrice ", market , " ", error) }
                }
            self.onFetchFinished()
        }
    }
}
