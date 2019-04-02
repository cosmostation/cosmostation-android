//
//  MainTabHistoryViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 05/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit
import Alamofire
import SafariServices

class MainTabHistoryViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var historyTableView: UITableView!
    @IBOutlet weak var emptyLabel: UILabel!
    
    var mainTabVC: MainTabViewController!
    var mHistories = Array<History.InnerHits>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        mainTabVC = (self.parent)?.parent as? MainTabViewController
        
        self.historyTableView.delegate = self
        self.historyTableView.dataSource = self
        self.historyTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.historyTableView.register(UINib(nibName: "HistoryCell", bundle: nil), forCellReuseIdentifier: "HistoryCell")
        
        onFetchHistory(mainTabVC.mAccount.account_address, "0", "100");
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = "";
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.mHistories.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:HistoryCell? = tableView.dequeueReusableCell(withIdentifier:"HistoryCell") as? HistoryCell
        let history = mHistories[indexPath.row]
        
        cell?.txTimeLabel.text = WUtils.nodeTimetoString(input: history._source.time)
        cell?.txBlockLabel.text = String(history._source.height) + " block"
        cell?.txHashLabel.text = history._source.hash
        cell?.txTypeLabel.text = WUtils.historyTitle(history._source.tx.value.msg)
        if(history._source.result.success) {
            cell?.txResultLabel.isHidden = true
        } else {
            cell?.txResultLabel.isHidden = false
        }
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let history = mHistories[indexPath.row]
        guard let url = URL(string: "https://www.mintscan.io/txs/" + history._source.hash) else { return }
        let safariViewController = SFSafariViewController(url: url)
        present(safariViewController, animated: true, completion: nil)
    }
    
    
    func onFetchHistory(_ address:String, _ from:String, _ size:String) {
        let query = "{\"from\": \"" + from + "\",\"size\": \"" + size + "\",\"query\": {\"multi_match\": {\"query\": \"" + address + "\",\"fields\": [\"tx.value.msg.value.delegator_address\", \"tx.value.msg.value.from_address\", \"tx.value.msg.value.to_address\", \"tx.value.msg.value.depositor\", \"tx.value.msg.value.voter\", \"tx.value.msg.value.input.address\", \"tx.value.msg.value.output.address\"]}},\"sort\": [{\"height\": {\"order\": \"desc\"}}]}"
        let data = query.data(using: .utf8)
        do {
            let params = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
            let request = Alamofire.request(CSS_ES_URL, method: .post, parameters: params, encoding: JSONEncoding.default, headers: [:])
            request.responseJSON { response in
                switch response.result {
                case .success(let res):
//                    print("res " , res)
                    guard let history = res as? [String : Any] else {
                        print("no history!!")
                        self.emptyLabel.isHidden = false
                        return;
                    }
                    let rawHistory = History.init(history)
                    print("rawHistory " , rawHistory.hits.hits.count)
                    
                    self.mHistories.removeAll()
                    self.mHistories = rawHistory.hits.hits
                    self.emptyLabel.isHidden = true
                    self.historyTableView.reloadData()
                    
                case .failure(let error):
                    print("error ", error)
                }
            }
            
        } catch {
            print(error)
        }
    }
}
