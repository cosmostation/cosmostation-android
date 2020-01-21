//
//  AllValidatorCell.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class AllValidatorCell: UITableViewCell {

    @IBOutlet weak var cardView: CardView!
    @IBOutlet weak var validatorImg: UIImageView!
    @IBOutlet weak var freeEventImg: UIImageView!
    @IBOutlet weak var revokedImg: UIImageView!
    @IBOutlet weak var monikerLabel: UILabel!
    @IBOutlet weak var powerLabel: UILabel!
    @IBOutlet weak var commissionLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        validatorImg.layer.borderWidth = 1
        validatorImg.layer.masksToBounds = false
        validatorImg.layer.borderColor = UIColor(hexString: "#4B4F54").cgColor
        validatorImg.layer.cornerRadius = validatorImg.frame.height/2
        validatorImg.clipsToBounds = true
        
        self.selectionStyle = .none
        
        powerLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
        commissionLabel.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: Font_12_caption1)
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
//        self.addRippleEffect(to: self.cardView)
    }
    
    override func prepareForReuse() {
        self.validatorImg.image = UIImage(named: "validatorNoneImg")
        self.monikerLabel.text = "-"
        self.powerLabel.text = "-"
        super.prepareForReuse()
    }
    
    func addRippleEffect(to referenceView: UIView) {
        /*! Creates a circular path around the view*/
        let path = UIBezierPath(ovalIn: CGRect(x: 0, y: 0, width: referenceView.bounds.size.width, height: referenceView.bounds.size.height))
        /*! Position where the shape layer should be */
        let shapePosition = CGPoint(x: referenceView.bounds.size.width / 2.0, y: referenceView.bounds.size.height / 2.0)
        let rippleShape = CAShapeLayer()
        rippleShape.bounds = CGRect(x: 0, y: 0, width: referenceView.bounds.size.width, height: referenceView.bounds.size.height)
        rippleShape.path = path.cgPath
        rippleShape.fillColor = UIColor.clear.cgColor
        rippleShape.strokeColor = UIColor.white.cgColor
        rippleShape.lineWidth = 4
        rippleShape.position = shapePosition
        rippleShape.opacity = 0
        
        /*! Add the ripple layer as the sublayer of the reference view */
        referenceView.layer.addSublayer(rippleShape)
        /*! Create scale animation of the ripples */
        let scaleAnim = CABasicAnimation(keyPath: "transform.scale")
        scaleAnim.fromValue = NSValue(caTransform3D: CATransform3DIdentity)
        scaleAnim.toValue = NSValue(caTransform3D: CATransform3DMakeScale(2, 2, 1))
        /*! Create animation for opacity of the ripples */
        let opacityAnim = CABasicAnimation(keyPath: "opacity")
        opacityAnim.fromValue = 1
        opacityAnim.toValue = nil
        /*! Group the opacity and scale animations */
        let animation = CAAnimationGroup()
        animation.animations = [scaleAnim, opacityAnim]
        animation.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.easeOut)
        animation.duration = CFTimeInterval(0.7)
        animation.repeatCount = 25
        animation.isRemovedOnCompletion = true
        rippleShape.add(animation, forKey: "rippleEffect")
    }
    
}
