//
//  GuideViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 09/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class GuideViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var guideTableView: UITableView!
    lazy var mGuides = Array<GuideCategory>()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.mGuides = WUtils.getGuideList()
        self.guideTableView.delegate = self
        self.guideTableView.dataSource = self
        self.guideTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        self.guideTableView.register(UINib(nibName: "GuideListTableViewCell", bundle: nil), forCellReuseIdentifier: "GuideListTableViewCell")
    }


    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_guide", comment: "")
        self.navigationItem.title = NSLocalizedString("title_guide", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
    

    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
         return mGuides.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:GuideListTableViewCell? = tableView.dequeueReusableCell(withIdentifier:"GuideListTableViewCell") as? GuideListTableViewCell
        let mGuide = mGuides[indexPath.row]
        
        cell?.categoryLabel.text = mGuide.category
        var titleViews: [UIView] = [cell!.title0View, cell!.title1View, cell!.title2View,
                                    cell!.title3View, cell!.title4View, cell!.title5View, cell!.title6View]
        var titleLabels: [UILabel] = [cell!.title0Label, cell!.title1Label, cell!.title2Label,
                                    cell!.title3Label, cell!.title4Label, cell!.title5Label, cell!.title6Label]
        
        for i in 0 ..< titleViews.count {
            titleViews[i].isHidden = true
            titleViews[i].isUserInteractionEnabled = true
            let tappy = GuideTapGesture(target: self, action: #selector(self.tapped(sender:)))
            titleViews[i].addGestureRecognizer(tappy)
            tappy.category = indexPath.row
            tappy.title = i
        }
        
        for i in 0 ..< mGuide.titles.count {
            titleViews[i].isHidden = false
            titleLabels[i].text = mGuide.titles[i]
        }

        
        
        if(mGuide.expended) {
            cell?.separatorLine.isHidden = false
            cell?.detailStackView.isHidden = false
            cell?.expendArrowImg.image = UIImage.init(named: "dropdown_btn")
            
        } else {
            cell?.separatorLine.isHidden = true
            cell?.detailStackView.isHidden = true
            cell?.expendArrowImg.image = UIImage.init(named: "arrowNextGr")
            
        }
        return cell!
    }
    
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        let mGuide = mGuides[indexPath.row]
        if(mGuide.expended) {
            return CGFloat(60 + (mGuide.titles.count * 51))
        } else {
            return 60
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        mGuides[indexPath.row].expended = !mGuides[indexPath.row].expended
        self.guideTableView.reloadData()
    }

    @objc func tapped(sender : GuideTapGesture) {
        let guideDetialVC = GuideDetailViewController(nibName: "GuideDetailViewController", bundle: nil)
        guideDetialVC.guideData = mGuides[sender.category]
        guideDetialVC.guideDetail = sender.title
        self.navigationItem.title = ""
        self.navigationController?.pushViewController(guideDetialVC, animated: true)
    }
}

class GuideTapGesture: UITapGestureRecognizer {
    var category:Int = 0
    var title:Int = 0
}
