//
//  SBCardPopupViewController.swift
//  SBCardPopup
//
//  Created by Steve Barnegren on 22/06/2017.
//  Copyright © 2017 Steve Barnegren. All rights reserved.
//

import UIKit

public protocol SBCardPopupContent: class {
    weak var popupViewController: SBCardPopupViewController? {get set}
    var allowsTapToDismissPopupCard: Bool {get}
    var allowsSwipeToDismissPopupCard: Bool {get}
}

public class SBCardPopupViewController: UIViewController {
    
    var resultDelegate: SBCardPopupDelegate?
    
    // MARK: - Public Interface
    
    public var disableSwipeToDismiss = false
    public var disableTapToDismiss = false
    public var cornerRadius = CGFloat(7)
    
    public init(contentViewController viewController: UIViewController) {
        contentViewController = viewController
        contentView = viewController.view
        super.init(nibName: nil, bundle: nil)
    }
    
    public init(contentView view: UIView) {
        contentViewController = nil
        contentView = view
        super.init(nibName: nil, bundle: nil)
    }
    
    public func close() {
        animateOut()
    }
    
    public func show(onViewController viewController: UIViewController) {
        self.modalPresentationStyle = .overCurrentContext
        viewController.present(self, animated: false, completion: nil)
    }
    
    // MARK: - Types
    
    enum State {
        case animatingIn
        case idle
        case panning
        case animatingOut
        case physicsOut(PhysicsState)
    }
    
    struct PhysicsState {
        let acceleration = CGFloat(9999)
        var velocity = CGFloat(0)
    }
    
    // MARK: - Properties
    
    private let backgroundOpacity = CGFloat(0.85)
    
    private var displayLink: CADisplayLink!
    private var lastTimeStamp: CFTimeInterval?
    
    private let contentViewController: UIViewController?
    private let contentView: UIView
    
    fileprivate let containerView = UIView(frame: .zero)
    
    private var hasAnimatedIn = false
    
    private var containerCenterYConstraint: NSLayoutConstraint!
    private var containerOffscreenConstraint: NSLayoutConstraint!
    
    fileprivate var tapRecognizer: UITapGestureRecognizer!
    fileprivate var panRecognizer: UIPanGestureRecognizer!
    
    private var swipeOffset = CGFloat(0)
    
    fileprivate var popupProtocolResponder: SBCardPopupContent? {
        
        if let contentViewController = contentViewController {
            if let protocolResponder = contentViewController as? SBCardPopupContent {
                return protocolResponder
            }
        }
        else if let protocolResponder = contentView as? SBCardPopupContent {
            return protocolResponder
        }
        
        return nil
    }
    
    private var state = State.animatingIn
    
    // MARK: - UIViewController
    
    required public init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override public func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = UIColor.clear
        
        // Container view
        containerView.layer.cornerRadius = cornerRadius
        containerView.layer.masksToBounds = true
        view.addSubview(containerView)
        containerView.isUserInteractionEnabled = false
        
        // Content
        if let contentViewController = contentViewController {
            addChild(contentViewController)
        }
        containerView.addSubview(contentView)
        
        // Popup Protocol Responder
        popupProtocolResponder?.popupViewController = self
        
        // Apply Constraints
        applyContainerViewConstraints()
        applyContentViewConstraints()
        containerOffscreenConstraint.isActive = true
        
        // Tap Away Recognizer
        tapRecognizer = UITapGestureRecognizer(target: self, action: #selector(tapAway))
        tapRecognizer.delegate = self
        view.addGestureRecognizer(tapRecognizer)
        
        // Pan Recognizer
        panRecognizer = UIPanGestureRecognizer(target: self, action: #selector(didPan))
        panRecognizer.delegate = self
        view.addGestureRecognizer(panRecognizer)
        
        // Display Link
        displayLink = CADisplayLink(target: self, selector: #selector(tick))
//        displayLink.add(to: .current, forMode: .RunLoop.Mode.common)
        
        displayLink.add(to: .current, forMode: .common)
    }
    
    public override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        if !hasAnimatedIn {
            animateIn()
            hasAnimatedIn = true
        }
    }
    
    public override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        displayLink.invalidate()
    }
    
    public override func updateViewConstraints() {
        super.updateViewConstraints()
        
        // Elastic Pull upwards
        if swipeOffset < 0 {
            
            let offset = -swipeOffset
            let offsetPct = (offset / view.bounds.size.width/2)
            let elasticity = CGFloat(3)
            let percent = offsetPct / (1.0 + (offsetPct * elasticity))
            
            containerCenterYConstraint.constant = -(percent * view.bounds.size.width/2)
        }
            // Regular tracking downwards
        else{
            containerCenterYConstraint.constant = swipeOffset
        }
        
    }
    
    public override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        
        // Update background color and card opacity if panning or physics out
        switch state {
        case .animatingIn: break
        case .idle: break
        case .animatingOut: break
        case .panning: fallthrough
        case .physicsOut:
            
            let distance = view.bounds.size.height/2 + contentView.frame.size.height/2
            
            var outPct = 1.0 - (swipeOffset / distance)
            outPct = min(outPct, 1.0)
            let opacity = backgroundOpacity * outPct
            view.backgroundColor = UIColor(white: 0, alpha: opacity)
            
            containerView.alpha = outPct
        }
        
    }
    
    // MARK: - Constraints
    
    private func applyContentViewConstraints() {
        
        contentView.translatesAutoresizingMaskIntoConstraints = false
        
        [NSLayoutConstraint.Attribute.left, .right, .top, .bottom].forEach{
            
            let constraint = NSLayoutConstraint(item: contentView,
                                                attribute: $0,
                                                relatedBy: .equal,
                                                toItem: containerView,
                                                attribute: $0,
                                                multiplier: 1.0,
                                                constant: 0)
            containerView.addConstraint(constraint)
        }
    }
    
    private func applyContainerViewConstraints() {
        
        containerView.translatesAutoresizingMaskIntoConstraints = false
        
        let sideMargin = CGFloat(20)
        let verticalMargins = CGFloat(20)
        
        let left = NSLayoutConstraint(item: containerView,
                                      attribute: .left,
                                      relatedBy: .equal,
                                      toItem: view,
                                      attribute: .left,
                                      multiplier: 1.0,
                                      constant: sideMargin)
        
        let right = NSLayoutConstraint(item: containerView,
                                       attribute: .right,
                                       relatedBy: .equal,
                                       toItem: view,
                                       attribute: .right,
                                       multiplier: 1.0,
                                       constant: -sideMargin)
        
        containerCenterYConstraint = NSLayoutConstraint(item: containerView,
                                                        attribute: .centerY,
                                                        relatedBy: .equal,
                                                        toItem: view,
                                                        attribute: .centerY,
                                                        multiplier: 1.0,
                                                        constant: 0)
        containerCenterYConstraint.priority = UILayoutPriority.defaultLow
        
        let limitHeight = NSLayoutConstraint(item: containerView,
                                             attribute: .height,
                                             relatedBy: .lessThanOrEqual,
                                             toItem: view,
                                             attribute: .height,
                                             multiplier: 1.0,
                                             constant: -verticalMargins*2)
        limitHeight.priority = UILayoutPriority.defaultHigh
        
        containerOffscreenConstraint = NSLayoutConstraint(item: containerView,
                                                          attribute: .top,
                                                          relatedBy: .equal,
                                                          toItem: view,
                                                          attribute: .bottom,
                                                          multiplier: 1.0,
                                                          constant: 0)
        containerOffscreenConstraint.priority = UILayoutPriority.required
        
        view.addConstraints([left, right, containerCenterYConstraint, limitHeight, containerOffscreenConstraint])
    }
    
    private func pinContainerOffscreen(_ pinOffscreen: Bool) {
        containerOffscreenConstraint.isActive = pinOffscreen
    }
    
    
    // MARK: - Animation
    
    private func animateIn() {
        
        let duration = 0.49
        
        // Animate background color
        UIView.animate(withDuration: duration,
                       delay: 0.0,
                       options: [.curveEaseInOut, .allowUserInteraction],
                       animations: {
                        self.view.backgroundColor = UIColor(white: 0, alpha: self.backgroundOpacity)
        }, completion: nil)
        
        // Animate container on screen
        containerOffscreenConstraint.isActive = false
        self.view.setNeedsUpdateConstraints()
        
        UIView.animate(withDuration: duration,
                       delay: 0.0,
                       usingSpringWithDamping: 0.84,
                       initialSpringVelocity: 0,
                       options: [.allowUserInteraction],
                       animations: {
                        self.view.layoutIfNeeded()
        }, completion: {
            _ in
            self.containerView.isUserInteractionEnabled = true
            self.state = .idle
        })
    }
    
    private func animateOut() {
        view.isUserInteractionEnabled = false
        state = .animatingOut
        
        let duration = 0.6
        
        // Animate background color
        UIView.animate(withDuration: duration,
                       delay: 0.0,
                       options: [.curveEaseInOut],
                       animations: {
                        self.view.backgroundColor = UIColor.clear
        }, completion: nil)
        
        // Animate container off screen
        containerOffscreenConstraint.isActive = true
        view.setNeedsUpdateConstraints()
        
        UIView.animate(withDuration: duration,
                       delay: 0.0,
                       usingSpringWithDamping: 0.8,
                       initialSpringVelocity: 0,
                       options: [],
                       animations: {
                        self.view.layoutIfNeeded()
        }, completion: {
            _ in
            self.dismiss(animated: false, completion: nil)
        })
    }
    
    private func animate(fromPan panRecognizer: UIPanGestureRecognizer) {
        
        let animateOutThreshold = CGFloat(50)
        
        let velocity = panRecognizer.velocity(in: view).y
        
        // Animate out
        if velocity > animateOutThreshold {
            let physicsState = PhysicsState(velocity: velocity)
            state = .physicsOut(physicsState)
        }
            // Snap back
        else {
            animateSnapBackToCenter()
        }
    }
    
    private func animateSnapBackToCenter() {
        
        let duration = 0.4
        
        swipeOffset = 0
        view.setNeedsUpdateConstraints()
        //view.setNeedsLayout()
        
        UIView.animate(withDuration: duration,
                       delay: 0.0,
                       usingSpringWithDamping: 0.7,
                       initialSpringVelocity: 0,
                       options: [],
                       animations: {
                        self.view.layoutIfNeeded()
        }, completion: { _ in })
    }
    
    // MARK: - Gestures
    
    @objc private func tapAway() {
        
        if let protocolResponder = popupProtocolResponder {
            if protocolResponder.allowsTapToDismissPopupCard {
                animateOut()
            }
        }
        else{
            animateOut()
        }
        
    }
    
    @objc private func didPan(recognizer: UIPanGestureRecognizer) {
        
        if state == .animatingIn {
            state = .idle
            self.view.layer.removeAllAnimations()
            self.containerView.layer.removeAllAnimations()
        }
        
        guard
            state == .idle || state == .panning
            else { return }
        
        let applyOffset = {
            self.swipeOffset = recognizer.translation(in: self.view).y
            self.view.setNeedsUpdateConstraints()
        }
        
        switch recognizer.state {
        case .possible:
            break
        case .began:
            state = .panning
            applyOffset()
        case .changed:
            state = .panning
            applyOffset()
        case .cancelled:
            break
        case .failed:
            break
        case .ended:
            animate(fromPan: recognizer)
        }
        
    }
    
    // MARK: - Display Link
    
    @objc func tick() {
        
        // We need a previous time stamp to work with, bail if we don't have one
        guard let last = lastTimeStamp else {
            lastTimeStamp = displayLink.timestamp
            return
        }
        
        // Work out dt
        let dt = displayLink.timestamp - last
        
        // Save the current time
        lastTimeStamp = displayLink.timestamp
        
        // If we're using physics to animate out, update the simulation
        guard case var State.physicsOut(physicsState) = state else {
            return
        }
        
        physicsState.velocity += CGFloat(dt) * physicsState.acceleration
        
        swipeOffset += physicsState.velocity * CGFloat(dt)
        
        view.setNeedsUpdateConstraints()
        state = .physicsOut(physicsState)
        
        // Remove if the content view is off screen
        if swipeOffset > view.bounds.size.height / 2 {
            dismiss(animated: false, completion: nil)
        }
        
    }
    
}

protocol SBCardPopupDelegate{
    func SBCardPopupResponse(type:Int, result:Int)
}

extension SBCardPopupViewController: UIGestureRecognizerDelegate {
    
    public func gestureRecognizerShouldBegin(_ gestureRecognizer: UIGestureRecognizer) -> Bool {
        
        // Pan, check if swipe enabled
        if let responder = popupProtocolResponder, gestureRecognizer === panRecognizer {
            
            if self.disableSwipeToDismiss {
                return false
            }
            
            return responder.allowsSwipeToDismissPopupCard
        }
        
        // Tap, check if is outside view
        if gestureRecognizer === tapRecognizer {
            
            if self.disableTapToDismiss {
                return false
            }
            
            let location = tapRecognizer.location(in: view)
            return !containerView.frame.contains(location)
        }
        
        return true
    }
}

func ==(lhs: SBCardPopupViewController.State, rhs: SBCardPopupViewController.State) -> Bool {
    
    switch (lhs, rhs) {
    case (.animatingIn, .animatingIn):
        return true
    case (.idle, .idle):
        return true
    case (.panning, .panning):
        return true
    case (.physicsOut, .physicsOut):
        return true
    default:
        return false
    }
}
