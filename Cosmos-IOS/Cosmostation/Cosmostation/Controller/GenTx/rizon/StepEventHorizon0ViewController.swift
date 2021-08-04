//
//  StepEventHorizon0ViewController.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/02.
//  Copyright © 2021 wannabit. All rights reserved.
//

import UIKit
import SwiftKeychainWrapper
import Alamofire

class StepEventHorizon0ViewController: BaseViewController, UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout, SBCardPopupDelegate {
    
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
    
    var allMnemonicWords = [String]()
    var filteredMnemonicWords = [String]()
    var userInputWords = [String]()
    var mCurrentPosition = 0;
    var pageHolderVC: StepGenTxViewController!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.account = BaseData.instance.selectAccountById(id: BaseData.instance.getRecentAccountId())
        self.chainType = WUtils.getChainType(account!.account_base_chain)
        self.pageHolderVC = self.parent as? StepGenTxViewController
        
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
        self.updateFocus()
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
    
    
    @IBAction func onKeyClick(_ sender: UIButton) {
        let appendedText = (self.mNemonicInputs[mCurrentPosition].text)?.appending(sender.titleLabel?.text ?? "")
        self.mNemonicInputs[mCurrentPosition].text = appendedText
        updateCollectionView()
    }
    
    @IBAction func onDeleteClick(_ sender: UIButton) {
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
    }
    
    @IBAction func onSpaceClick(_ sender: UIButton) {
        if(mCurrentPosition < 23) {
            mCurrentPosition = mCurrentPosition + 1
        }
        updateFocus()
    }
    
    
    @IBAction func onCancelClick(_ sender: UIButton) {
        pageHolderVC.onBeforePage()
    }
    
    @IBAction func onPasteClick(_ sender: UIButton) {
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
    }
    
    @IBAction func onConfirmClick(_ sender: UIButton) {
        if (!onValidateUserinput()) {
            self.onShowToast(NSLocalizedString("error_recover_mnemonic", comment: ""))
        } else {
            self.onCheckHdacBalance()
        }
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
        if (userInputWords.count < 3) {
            return false
        }
        for input in userInputWords {
            if (!allMnemonicWords.contains(input)) {
                return false
            }
        }
        return true
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
        updateFocus()
    }
    
    
    func onCheckHdacBalance() {
        print("onCheckHdacBalance")
        self.showWaittingAlert()
        DispatchQueue.global().async {
            var seed = ""
            self.userInputWords.forEach { word in seed = seed + " " + word }
            let key = HdacUtil.getKey(seed.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines))
            let address = HdacUtil.generateHdacAddress(self.chainType, key.publicKey)
            
            DispatchQueue.main.async(execute: {
                let request = Alamofire.request(BaseNetWork.hdacBalance(self.chainType, address), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
                request.responseJSON { (response) in
                    switch response.result {
                    case .success(let res):
                        //                print("res ", res)
                        var hdacUtxos = Array<HdacUtxo>()
                        if let responseDatas = res as? Array<NSDictionary> {
                            responseDatas.forEach { responseData in
                                hdacUtxos.append(HdacUtxo.init(responseData))
                            }
                        }
                        print("hdacUtxos ", hdacUtxos.count)
                        print("balance ", HdacUtil.getBalacne(self.chainType, hdacUtxos))
                        
                        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(500)) {
                            let popupVC = HdacBalancePopup(nibName: "HdacBalancePopup", bundle: nil)
                            popupVC.address = address
                            popupVC.amount = HdacUtil.getBalacne(self.chainType, hdacUtxos)
                            let wcPopup = SBCardPopupViewController(contentViewController: popupVC)
                            wcPopup.resultDelegate = self
                            wcPopup.show(onViewController: self)
                        }
                        
                    case .failure(let error):
                        print("onCheckHdacBalanceV1 ", error)
                    }
                    self.hideWaittingAlert()
                }
            });
        }
    }
    
    
    func SBCardPopupResponse(type: Int, result: Int) {
        print("SBCardPopupResponse ", type, "  ", result)
        if (result == 1) {
            self.showWaittingAlert()
            DispatchQueue.global().async {
                var seed = ""
                self.userInputWords.forEach { word in seed = seed + " " + word }
                self.pageHolderVC.mHdacKey = HdacUtil.getKey(seed.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines))
                self.pageHolderVC.mHdacAddress = HdacUtil.generateHdacAddress(self.chainType, self.pageHolderVC.mHdacKey!.publicKey)
                
                DispatchQueue.main.async(execute: {
                    let request = Alamofire.request(BaseNetWork.hdacBalance(self.chainType, self.pageHolderVC!.mHdacAddress!), method: .get, parameters: [:], encoding: URLEncoding.default, headers: [:])
                    request.responseJSON { (response) in
                        switch response.result {
                        case .success(let res):
                            var hdacUtxos = Array<HdacUtxo>()
                            if let responseDatas = res as? Array<NSDictionary> {
                                responseDatas.forEach { responseData in
                                    hdacUtxos.append(HdacUtxo.init(responseData))
                                }
                            }
                            self.pageHolderVC.mHdacBalance = HdacUtil.getBalacne(self.chainType, hdacUtxos)
                            self.pageHolderVC.mHdacUTXOs = hdacUtxos
                            self.pageHolderVC.onNextPage()
                            
                        case .failure(let error):
                            print("onCheckHdacBalanceV2 ", error)
                        }
                        self.hideWaittingAlert()
                    }
                });
            }
        }
    }
}
