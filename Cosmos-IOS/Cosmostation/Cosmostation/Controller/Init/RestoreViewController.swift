//
//  RestoreViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 28/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class RestoreViewController: BaseViewController , UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout, PasswordViewDelegate{
    
    @IBOutlet weak var topView: UIView!
    @IBOutlet weak var cardView: CardView!
    @IBOutlet weak var actionView: UIStackView!
    @IBOutlet weak var keyboardView: UIView!
    @IBOutlet weak var mNeminicImg: UIImageView!
    
    @IBOutlet weak var mNemonicInput0: BottomLineTextField!
    @IBOutlet weak var mNemonicInput1: BottomLineTextField!
    @IBOutlet weak var mNemonicInput2: BottomLineTextField!
    @IBOutlet weak var mNemonicInput3: BottomLineTextField!
    @IBOutlet weak var mNemonicInput4: BottomLineTextField!
    @IBOutlet weak var mNemonicInput5: BottomLineTextField!
    @IBOutlet weak var mNemonicInput6: BottomLineTextField!
    @IBOutlet weak var mNemonicInput7: BottomLineTextField!
    @IBOutlet weak var mNemonicInput8: BottomLineTextField!
    @IBOutlet weak var mNemonicInput9: BottomLineTextField!
    @IBOutlet weak var mNemonicInput10: BottomLineTextField!
    @IBOutlet weak var mNemonicInput11: BottomLineTextField!
    @IBOutlet weak var mNemonicInput12: BottomLineTextField!
    @IBOutlet weak var mNemonicInput13: BottomLineTextField!
    @IBOutlet weak var mNemonicInput14: BottomLineTextField!
    @IBOutlet weak var mNemonicInput15: BottomLineTextField!
    @IBOutlet weak var mNemonicInput16: BottomLineTextField!
    @IBOutlet weak var mNemonicInput17: BottomLineTextField!
    @IBOutlet weak var mNemonicInput18: BottomLineTextField!
    @IBOutlet weak var mNemonicInput19: BottomLineTextField!
    @IBOutlet weak var mNemonicInput20: BottomLineTextField!
    @IBOutlet weak var mNemonicInput21: BottomLineTextField!
    @IBOutlet weak var mNemonicInput22: BottomLineTextField!
    @IBOutlet weak var mNemonicInput23: BottomLineTextField!
    
    var mNemonicInputs: [BottomLineTextField] = [BottomLineTextField]()
    
    @IBOutlet weak var suggestCollectionView: UICollectionView!
    @IBOutlet weak var wordCntLabel: UILabel!
    var allMnemonicWords = [String]()
    var filteredMnemonicWords = [String]()
    var userInputWords = [String]()
    var mCurrentPosition = 0;
    
    override func viewDidLoad() {
        super.viewDidLoad()
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
            self.chainType = ChainType.SUPPORT_CHAIN_COSMOS_MAIN
            self.initViewUpdate()
        })
        cosmosAction.setValue(UIColor.black, forKey: "titleTextColor")
        cosmosAction.setValue(UIImage(named: "cosmosWhMain")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let irisAction = UIAlertAction(title: NSLocalizedString("chain_title_iris", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.SUPPORT_CHAIN_IRIS_MAIN
            self.initViewUpdate()
        })
        irisAction.setValue(UIColor.black, forKey: "titleTextColor")
        irisAction.setValue(UIImage(named: "irisWh")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let bnbAction = UIAlertAction(title: NSLocalizedString("chain_title_bnb", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.SUPPORT_CHAIN_BINANCE_MAIN
            self.initViewUpdate()
        })
        bnbAction.setValue(UIColor.black, forKey: "titleTextColor")
        bnbAction.setValue(UIImage(named: "binanceChImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        let iovAction = UIAlertAction(title: NSLocalizedString("chain_title_iov", comment: ""), style: .default, handler: {_ in
            self.chainType = ChainType.SUPPORT_CHAIN_IOV_MAIN
            self.initViewUpdate()
        })
        iovAction.setValue(UIColor.black, forKey: "titleTextColor")
        iovAction.setValue(UIImage(named: "iovImg")?.withRenderingMode(.alwaysOriginal), forKey: "image")
        
        showAlert.addAction(cosmosAction)
        showAlert.addAction(irisAction)
        showAlert.addAction(bnbAction)
        showAlert.addAction(iovAction)
        self.present(showAlert, animated: true, completion: nil)
    }
    
    func initViewUpdate() {
        self.mNeminicImg.image = self.mNeminicImg.image?.withRenderingMode(UIImage.RenderingMode.alwaysTemplate)
        self.mNeminicImg.tintColor = WUtils.getChainColor(self.chainType!)
        self.topView.isHidden = false
        self.cardView.isHidden = false
        self.actionView.isHidden = false
        self.keyboardView.isHidden = false
//        self.updateFocus()
        
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
        for i in 0 ..< self.mNemonicInputs.count {
            self.mNemonicInputs[i].hasFocused = false
        }
        self.mNemonicInputs[mCurrentPosition].hasFocused = true
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
            if(mCurrentPosition > 0) {
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
    }
    
    func passwordResponse(result: Int) {
        if (result == PASSWORD_RESUKT_OK) {
            DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(610), execute: {
                let restorePathVC = UIStoryboard(name: "Init", bundle: nil).instantiateViewController(withIdentifier: "RestorePathViewController") as! RestorePathViewController
                self.navigationItem.title = ""
                restorePathVC.userInputWords = self.userInputWords
                restorePathVC.userChain = self.chainType
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
