//
//  RestoreViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 28/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import SwiftKeychainWrapper

class RestoreViewController: BaseViewController , UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout, PasswordViewDelegate{
    
    @IBOutlet weak var topView: UIView!
    @IBOutlet weak var cardView: CardView!
    @IBOutlet weak var actionView: UIStackView!
    @IBOutlet weak var keyboardView: UIView!
    @IBOutlet weak var mNeminicImg: UIImageView!
    
    @IBOutlet weak var mNemonicLayer0: BottomLineView!
    @IBOutlet weak var mNemonicLayer1: BottomLineView!
    @IBOutlet weak var mNemonicLayer2: BottomLineView!
    @IBOutlet weak var mNemonicLayer3: BottomLineView!
    @IBOutlet weak var mNemonicLayer4: BottomLineView!
    @IBOutlet weak var mNemonicLayer5: BottomLineView!
    @IBOutlet weak var mNemonicLayer6: BottomLineView!
    @IBOutlet weak var mNemonicLayer7: BottomLineView!
    @IBOutlet weak var mNemonicLayer8: BottomLineView!
    @IBOutlet weak var mNemonicLayer9: BottomLineView!
    @IBOutlet weak var mNemonicLayer10: BottomLineView!
    @IBOutlet weak var mNemonicLayer11: BottomLineView!
    @IBOutlet weak var mNemonicLayer12: BottomLineView!
    @IBOutlet weak var mNemonicLayer13: BottomLineView!
    @IBOutlet weak var mNemonicLayer14: BottomLineView!
    @IBOutlet weak var mNemonicLayer15: BottomLineView!
    @IBOutlet weak var mNemonicLayer16: BottomLineView!
    @IBOutlet weak var mNemonicLayer17: BottomLineView!
    @IBOutlet weak var mNemonicLayer18: BottomLineView!
    @IBOutlet weak var mNemonicLayer19: BottomLineView!
    @IBOutlet weak var mNemonicLayer20: BottomLineView!
    @IBOutlet weak var mNemonicLayer21: BottomLineView!
    @IBOutlet weak var mNemonicLayer22: BottomLineView!
    @IBOutlet weak var mNemonicLayer23: BottomLineView!
    
    @IBOutlet weak var mNemonicInput0: UITextField!
    @IBOutlet weak var mNemonicInput1: UITextField!
    @IBOutlet weak var mNemonicInput2: UITextField!
    @IBOutlet weak var mNemonicInput3: UITextField!
    @IBOutlet weak var mNemonicInput4: UITextField!
    @IBOutlet weak var mNemonicInput5: UITextField!
    @IBOutlet weak var mNemonicInput6: UITextField!
    @IBOutlet weak var mNemonicInput7: UITextField!
    @IBOutlet weak var mNemonicInput8: UITextField!
    @IBOutlet weak var mNemonicInput9: UITextField!
    @IBOutlet weak var mNemonicInput10: UITextField!
    @IBOutlet weak var mNemonicInput11: UITextField!
    @IBOutlet weak var mNemonicInput12: UITextField!
    @IBOutlet weak var mNemonicInput13: UITextField!
    @IBOutlet weak var mNemonicInput14: UITextField!
    @IBOutlet weak var mNemonicInput15: UITextField!
    @IBOutlet weak var mNemonicInput16: UITextField!
    @IBOutlet weak var mNemonicInput17: UITextField!
    @IBOutlet weak var mNemonicInput18: UITextField!
    @IBOutlet weak var mNemonicInput19: UITextField!
    @IBOutlet weak var mNemonicInput20: UITextField!
    @IBOutlet weak var mNemonicInput21: UITextField!
    @IBOutlet weak var mNemonicInput22: UITextField!
    @IBOutlet weak var mNemonicInput23: UITextField!

    var mNemonicLayers: [BottomLineView] = [BottomLineView]()
    var mNemonicInputs: [UITextField] = [UITextField]()
    
    @IBOutlet weak var suggestCollectionView: UICollectionView!
    @IBOutlet weak var wordCntLabel: UILabel!
    var allMnemonicWords = [String]()
    var filteredMnemonicWords = [String]()
    var userInputWords = [String]()
    var mCurrentPosition = 0;
    var usingBip44:Bool = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.mNemonicLayers = [self.mNemonicLayer0, self.mNemonicLayer1, self.mNemonicLayer2, self.mNemonicLayer3,
                               self.mNemonicLayer4, self.mNemonicLayer5, self.mNemonicLayer6, self.mNemonicLayer7,
                               self.mNemonicLayer8, self.mNemonicLayer9, self.mNemonicLayer10, self.mNemonicLayer11,
                               self.mNemonicLayer12, self.mNemonicLayer13, self.mNemonicLayer14, self.mNemonicLayer15,
                               self.mNemonicLayer16, self.mNemonicLayer17, self.mNemonicLayer18, self.mNemonicLayer19,
                               self.mNemonicLayer20, self.mNemonicLayer21, self.mNemonicLayer22, self.mNemonicLayer23]
        
        self.mNemonicInputs = [self.mNemonicInput0, self.mNemonicInput1, self.mNemonicInput2, self.mNemonicInput3,
                               self.mNemonicInput4, self.mNemonicInput5, self.mNemonicInput6, self.mNemonicInput7,
                               self.mNemonicInput8, self.mNemonicInput9, self.mNemonicInput10, self.mNemonicInput11,
                               self.mNemonicInput12, self.mNemonicInput13, self.mNemonicInput14, self.mNemonicInput15,
                               self.mNemonicInput16, self.mNemonicInput17, self.mNemonicInput18, self.mNemonicInput19,
                               self.mNemonicInput20, self.mNemonicInput21, self.mNemonicInput22, self.mNemonicInput23]
        
        for i in 0 ..< self.mNemonicInputs.count {
            self.mNemonicInputs[i].inputView = UIView();
            self.mNemonicInputs[i].tag = i
            self.mNemonicInputs[i].addTarget(self, action: #selector(myTargetFunction), for: UIControl.Event.editingDidBegin)
        }
        
        for word in WKey.english {
            allMnemonicWords.append(String(word))
        }
        
        self.suggestCollectionView.delegate = self
        self.suggestCollectionView.dataSource = self
        self.suggestCollectionView.register(UINib(nibName: "MnemonicCell", bundle: nil), forCellWithReuseIdentifier: "MnemonicCell")
        
        self.topView.isHidden = true
        self.cardView.isHidden = true
        self.actionView.isHidden = true
        self.keyboardView.isHidden = true
        
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_restore", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(title: NSLocalizedString("clear_all", comment: ""), style: .done, target: self, action: #selector(clearAll))
        
        if (chainType == nil) {
            self.onShowChainType()
        } else {
            self.initViewUpdate()
        }
    }
    
    func onShowChainType() {
        let showAlert = UIAlertController(title: nil, message: nil, preferredStyle: .alert)
        let cosmosAction = UIAlertAction(title: NSLocalizedString("chain_title_cosmos", comment: ""), style: .default, handler: { _ in
            self.chainType = ChainType.COSMOS_MAIN
            self.initViewUpdate()
        })
        cosmosAction.setValue(UIImage(named: "cosmosWhMain")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let irisAction = UIAlertAction(title: NSLocalizedString("chain_title_iris", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.IRIS_MAIN
            self.initViewUpdate()
        })
        irisAction.setValue(UIImage(named: "irisWh")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let bnbAction = UIAlertAction(title: NSLocalizedString("chain_title_bnb", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.BINANCE_MAIN
            self.initViewUpdate()
        })
        bnbAction.setValue(UIImage(named: "binanceChImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let iovAction = UIAlertAction(title: NSLocalizedString("chain_title_iov", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.IOV_MAIN
            self.initViewUpdate()
        })
        iovAction.setValue(UIImage(named: "iovChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let kavaAction = UIAlertAction(title: NSLocalizedString("chain_title_kava", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.KAVA_MAIN
            self.initViewUpdate()
        })
        kavaAction.setValue(UIImage(named: "kavaImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let bandAction = UIAlertAction(title: NSLocalizedString("chain_title_band", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.BAND_MAIN
            self.initViewUpdate()
        })
        bandAction.setValue(UIImage(named: "bandChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let persisAction = UIAlertAction(title: NSLocalizedString("chain_title_persis", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.PERSIS_MAIN
            self.initViewUpdate()
        })
        persisAction.setValue(UIImage(named: "chainpersistence")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        
        let secretAction = UIAlertAction(title: NSLocalizedString("chain_title_secret", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.SECRET_MAIN
            self.initViewUpdate()
        })
        secretAction.setValue(UIImage(named: "secretChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let certikAction = UIAlertAction(title: NSLocalizedString("chain_title_certik", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.CERTIK_MAIN
            self.initViewUpdate()
        })
        certikAction.setValue(UIImage(named: "certikChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let akashAction = UIAlertAction(title: NSLocalizedString("chain_title_akash", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.AKASH_MAIN
            self.initViewUpdate()
        })
        akashAction.setValue(UIImage(named: "akashChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let okexAction = UIAlertAction(title: NSLocalizedString("chain_title_okex", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.OKEX_MAIN
            self.initViewUpdate()
        })
        okexAction.setValue(UIImage(named: "okexChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        
        let cosmosTestAction = UIAlertAction(title: NSLocalizedString("chain_title_test_cosmos", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.COSMOS_TEST
            self.initViewUpdate()
        })
        cosmosTestAction.setValue(UIImage(named: "cosmosTestChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let irisTestAction = UIAlertAction(title: NSLocalizedString("chain_title_test_iris", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.IRIS_TEST
            self.initViewUpdate()
        })
        irisTestAction.setValue(UIImage(named: "irisTestChainImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let bnbTestAction = UIAlertAction(title: NSLocalizedString("chain_title_test_bnb", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.BINANCE_TEST
            self.initViewUpdate()
        })
        bnbTestAction.setValue(UIImage(named: "binancetestnet")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let kavaTestAction = UIAlertAction(title: NSLocalizedString("chain_title_kava_test", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.KAVA_TEST
            self.initViewUpdate()
        })
        kavaTestAction.setValue(UIImage(named: "kavaTestImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let iovTestAction = UIAlertAction(title: NSLocalizedString("chain_title_iov_test", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.IOV_TEST
            self.initViewUpdate()
        })
        iovTestAction.setValue(UIImage(named: "iovTestnetImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let okTestAction = UIAlertAction(title: NSLocalizedString("chain_title_okex_test", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.OKEX_TEST
            self.initViewUpdate()
        })
        okTestAction.setValue(UIImage(named: "okexTestnetImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let certikTestAction = UIAlertAction(title: NSLocalizedString("chain_title_certik_test", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.CERTIK_TEST
            self.initViewUpdate()
        })
        certikTestAction.setValue(UIImage(named: "certikTestnetImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        showAlert.addAction(cosmosAction)
        showAlert.addAction(irisAction)
        showAlert.addAction(bnbAction)
        showAlert.addAction(okexAction)
        showAlert.addAction(kavaAction)
        showAlert.addAction(bandAction)
        showAlert.addAction(iovAction)
        showAlert.addAction(certikAction)
        showAlert.addAction(akashAction)
        showAlert.addAction(secretAction)
        
        
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.COSMOS_TEST)) {
            showAlert.addAction(cosmosTestAction)
        }
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.IRIS_TEST)) {
            showAlert.addAction(irisTestAction)
        }
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.BINANCE_TEST)) {
            showAlert.addAction(bnbTestAction)
        }
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.KAVA_TEST)) {
            showAlert.addAction(kavaTestAction)
        }
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.IOV_TEST)) {
            showAlert.addAction(iovTestAction)
        }
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.OKEX_TEST)) {
            showAlert.addAction(okTestAction)
        }
        if (ChainType.SUPPRT_CHAIN().contains(ChainType.CERTIK_TEST)) {
            showAlert.addAction(certikTestAction)
        }
        self.present(showAlert, animated: true, completion: nil)
    }
    
    func initViewUpdate() {
        self.mNeminicImg.image = self.mNeminicImg.image?.withRenderingMode(UIImage.RenderingMode.alwaysTemplate)
        self.mNeminicImg.tintColor = WUtils.getChainColor(self.chainType!)
        self.topView.isHidden = false
        self.cardView.isHidden = false
        self.actionView.isHidden = false
        self.keyboardView.isHidden = false
        self.updateFocus()
        
    }
    
    @objc func clearAll(sender: AnyObject) {
        userInputWords.removeAll()
        for i in 0 ..< self.mNemonicInputs.count {
            self.mNemonicInputs[i].text = ""
        }
        mCurrentPosition = 0
        updateFocus()
        updateWordCnt()
    }
    
    @objc func myTargetFunction(sender: UITextField) {
        mCurrentPosition = sender.tag
        updateFocus()
    }
    
    func updateFocus() {
        for i in 0 ..< self.mNemonicLayers.count {
            self.mNemonicLayers[i].hasFocused = false
        }
        self.mNemonicLayers[mCurrentPosition].hasFocused = true
        self.mNemonicInputs[mCurrentPosition].becomeFirstResponder()
        updateCollectionView()
    }
    
    func updateCollectionView() {
        filteredMnemonicWords.removeAll()
        if ((self.mNemonicInputs[mCurrentPosition].text?.count)! > 0) {
            let match = self.mNemonicInputs[mCurrentPosition].text
            filteredMnemonicWords = allMnemonicWords.filter { $0.starts(with: match ?? "") }
            if (mCurrentPosition == 23 && filteredMnemonicWords.count == 1 && (filteredMnemonicWords[0] == self.mNemonicInputs[mCurrentPosition].text)) {
                filteredMnemonicWords.removeAll()
            }
        }
        self.suggestCollectionView.reloadData()
    }
    
    func updateWordCnt() {
        var checkWords = [String]()
        checkWords.removeAll()
        for i in 0 ..< self.mNemonicInputs.count {
            if((self.mNemonicInputs[i].text?.count)! > 0) {
                checkWords.append(self.mNemonicInputs[i].text!)
            } else {
                break
            }
        }
        self.wordCntLabel.text = String(checkWords.count) + " words"
        if(!(checkWords.count == 12 || checkWords.count == 16 || checkWords.count == 24)) {
            self.wordCntLabel.textColor = UIColor.init(hexString: "f31963")
            return
        }
        for input in checkWords {
            if(!allMnemonicWords.contains(input)) {
                self.wordCntLabel.textColor = UIColor.init(hexString: "f31963")
                return
            }
        }
        self.wordCntLabel.textColor = WUtils.getChainColor(chainType!)
    }
    
    func onValidateUserinput() -> Bool {
        userInputWords.removeAll()
        for i in 0 ..< self.mNemonicInputs.count {
            if((self.mNemonicInputs[i].text?.count)! > 0) {
                userInputWords.append(self.mNemonicInputs[i].text!)
            } else {
                break
            }
        }
        if (!(userInputWords.count == 12 || userInputWords.count == 16 || userInputWords.count == 24)) {
            return false
        }
        for input in userInputWords {
            if (!allMnemonicWords.contains(input)) {
                return false
            }
        }
        if (BTCMnemonic.init(words: userInputWords, password: "", wordListType: .english) == nil) {
            return false
        }
        return true
    }
    
    
    @IBAction func onKeyClick(_ sender: UIButton) {
        let appendedText = (self.mNemonicInputs[mCurrentPosition].text)?.appending(sender.titleLabel?.text ?? "")
        self.mNemonicInputs[mCurrentPosition].text = appendedText
        updateCollectionView()
        updateWordCnt()
    }
    
    @IBAction func onDeleteClick(_ sender: Any) {
        if((self.mNemonicInputs[mCurrentPosition].text?.count)! > 0) {
            let subText = String(self.mNemonicInputs[mCurrentPosition].text?.dropLast() ?? "")
            self.mNemonicInputs[mCurrentPosition].text = subText
            updateCollectionView()
        } else {
            if (mCurrentPosition > 0) {
                mCurrentPosition = mCurrentPosition - 1
            } else {
                self.navigationController?.popViewController(animated: true)
                return
            }
            updateFocus()
        }
        updateWordCnt()
    }
    
    @IBAction func onSpaceClick(_ sender: Any) {
        if(mCurrentPosition < 23) {
            mCurrentPosition = mCurrentPosition + 1
        }
        updateFocus()
        updateWordCnt()
    }
    
    @IBAction func onPasteClick(_ sender: Any) {
        if let words = KeychainWrapper.standard.string(forKey: BaseData.instance.copySalt!)?.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ") {
            for i in 0 ..< self.mNemonicInputs.count {
                self.mNemonicInputs[i].text = ""
            }
            for i in 0 ..< self.mNemonicInputs.count {
                if (words.count > i) {
                    self.mNemonicInputs[i].text = words[i].replacingOccurrences(of: ",", with: "")
                        .replacingOccurrences(of: " ", with: "")
                }
            }
            if(words.count < 23) {
                mCurrentPosition = words.count
            } else {
                mCurrentPosition = 23
            }
            updateFocus()
            return;
        }
        if let myString = UIPasteboard.general.string {
            for i in 0 ..< self.mNemonicInputs.count {
                self.mNemonicInputs[i].text = ""
            }

            let userPaste : [String] = myString.trimmingCharacters(in: .whitespacesAndNewlines).components(separatedBy: " ")
            for i in 0 ..< self.mNemonicInputs.count {
                if(userPaste.count > i) {
                    self.mNemonicInputs[i].text = userPaste[i].replacingOccurrences(of: ",", with: "")
                        .replacingOccurrences(of: " ", with: "")
                }
            }
            if(userPaste.count < 23) {
                mCurrentPosition = userPaste.count
            } else {
                mCurrentPosition = 23
            }
            updateFocus()

        } else {
            self.onShowToast(NSLocalizedString("error_no_clipboard", comment: ""))

        }
        updateWordCnt()
    }
    
    @IBAction func onConfirmClick(_ sender: Any) {
        if(!onValidateUserinput()) {
            self.onShowToast(NSLocalizedString("error_recover_mnemonic", comment: ""))
            
        } else {
            if (self.chainType == ChainType.KAVA_MAIN || self.chainType == ChainType.KAVA_TEST) {
                self.onSelectBip44()
            } else if (self.chainType == ChainType.SECRET_MAIN) {
                self.onSelectBip44Secret()
            } else if (self.chainType == ChainType.OKEX_MAIN || self.chainType == ChainType.OKEX_TEST)  {
                self.onSelectKeyTypeForOKex()
            } else {
                self.onCheckPassword()
            }
        }
    }
    
    func onSelectBip44() {
        let selectAlert = UIAlertController(title: NSLocalizedString("select_new_path_title", comment: ""), message: "", preferredStyle: .alert)
        let paragraphStyle = NSMutableParagraphStyle()
        paragraphStyle.alignment = NSTextAlignment.left
        let messageText = NSMutableAttributedString(
            string: NSLocalizedString("select_new_path_msg", comment: ""),
            attributes: [
                NSAttributedString.Key.paragraphStyle: paragraphStyle,
                NSAttributedString.Key.font: UIFont.preferredFont(forTextStyle: UIFont.TextStyle.caption1)
            ]
        )
        selectAlert.setValue(messageText, forKey: "attributedMessage")
        selectAlert.addAction(UIAlertAction(title: NSLocalizedString("kava_old_path", comment: ""), style: .default, handler: { _ in
            self.usingBip44 = false
            self.onCheckPassword()
        }))
        selectAlert.addAction(UIAlertAction(title: NSLocalizedString("kava_new_path", comment: ""), style: .default, handler: { _ in
            self.usingBip44 = true
            self.onCheckPassword()
        }))
        self.present(selectAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            selectAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    //TODO app already release with new path so we just toggle "usingBip44" for secret case!(maintain warn!!)
    func onSelectBip44Secret() {
        let selectAlert = UIAlertController(title: NSLocalizedString("select_new_path_title", comment: ""), message: "", preferredStyle: .alert)
        let paragraphStyle = NSMutableParagraphStyle()
        paragraphStyle.alignment = NSTextAlignment.left
        let messageText = NSMutableAttributedString(
            string: NSLocalizedString("select_new_path_msg_secret", comment: ""),
            attributes: [
                NSAttributedString.Key.paragraphStyle: paragraphStyle,
                NSAttributedString.Key.font: UIFont.preferredFont(forTextStyle: UIFont.TextStyle.caption1)
            ]
        )
        selectAlert.setValue(messageText, forKey: "attributedMessage")
        selectAlert.addAction(UIAlertAction(title: NSLocalizedString("secret_old_path", comment: ""), style: .default, handler: { _ in
            self.usingBip44 = true
            self.onCheckPassword()
        }))
        selectAlert.addAction(UIAlertAction(title: NSLocalizedString("secret_new_path", comment: ""), style: .default, handler: { _ in
            self.usingBip44 = false
            self.onCheckPassword()
        }))
        self.present(selectAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            selectAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func onSelectKeyTypeForOKex() {
        let selectAlert = UIAlertController(title: NSLocalizedString("select_keytype_okex_title", comment: ""), message: "", preferredStyle: .alert)
        let paragraphStyle = NSMutableParagraphStyle()
        paragraphStyle.alignment = NSTextAlignment.left
        let messageText = NSMutableAttributedString(
            string: NSLocalizedString("select_keytype_okex_msg", comment: ""),
            attributes: [
                NSAttributedString.Key.paragraphStyle: paragraphStyle,
                NSAttributedString.Key.font: UIFont.preferredFont(forTextStyle: UIFont.TextStyle.caption1)
            ]
        )
        selectAlert.setValue(messageText, forKey: "attributedMessage")
        selectAlert.addAction(UIAlertAction(title: NSLocalizedString("keytype_okex_old", comment: ""), style: .default, handler: { _ in
            self.usingBip44 = false
            self.onCheckPassword()
        }))
        selectAlert.addAction(UIAlertAction(title: NSLocalizedString("keytype_okex_new", comment: ""), style: .default, handler: { _ in
            self.usingBip44 = true
            self.onCheckPassword()
        }))
        self.present(selectAlert, animated: true) {
            let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.dismissAlertController))
            selectAlert.view.superview?.subviews[0].addGestureRecognizer(tapGesture)
        }
    }
    
    func onCheckPassword() {
        let passwordVC = UIStoryboard(name: "Password", bundle: nil).instantiateViewController(withIdentifier: "PasswordViewController") as! PasswordViewController
        self.navigationItem.title = ""
        self.navigationController!.view.layer.add(WUtils.getPasswordAni(), forKey: kCATransition)
        passwordVC.resultDelegate = self
        if(!BaseData.instance.hasPassword()) {
            passwordVC.mTarget = PASSWORD_ACTION_INIT
        } else  {
            passwordVC.mTarget = PASSWORD_ACTION_SIMPLE_CHECK
        }
        self.navigationController?.pushViewController(passwordVC, animated: false)
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(610), execute: {
                let restorePathVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "RestorePathViewController") as! RestorePathViewController
                self.navigationItem.title = ""
                restorePathVC.userInputWords = self.userInputWords
                restorePathVC.userChain = self.chainType
                restorePathVC.usingBip44 = self.usingBip44
                self.navigationController?.pushViewController(restorePathVC, animated: true)
            })
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return filteredMnemonicWords.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "MnemonicCell", for: indexPath) as? MnemonicCell
        cell?.MnemonicLabel.text = filteredMnemonicWords[indexPath.row]
        return cell!
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: 80, height: 32)
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        self.mNemonicInputs[mCurrentPosition].text = filteredMnemonicWords[indexPath.row]
        if (mCurrentPosition < 23) {
            mCurrentPosition = mCurrentPosition + 1
        }
        updateWordCnt()
        updateFocus()
    }
}
