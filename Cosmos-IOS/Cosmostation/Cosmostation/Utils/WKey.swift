//
//  WKeyUtils.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
import BitcoinKit
import BinanceChain
import ed25519swift
import CryptoSwift

class WKey {
    
    public struct Ed25519Key {
        var key: [UInt8]
        var chainCode: [UInt8]

        public func derive(index: UInt32) -> Ed25519Key? {
            var bigEndian = index.bigEndian
            let data = Data(bytes: &bigEndian, count: MemoryLayout.size(ofValue: bigEndian))
            var bytes = [UInt8](data)
            bytes = [0] + key + bytes

            let hmac = HMAC(key: chainCode, variant: .sha512)
            guard let sum = try? hmac.authenticate(bytes) else { return nil }
            return Ed25519Key(key: Array(sum[0..<32]), chainCode: Array(sum[32..<64]))
        }
    }
    
    static func getMasterKeyFromWords(_ m: [String]) -> HDPrivateKey {
        return HDPrivateKey(seed: Mnemonic.seed(mnemonic: m), network: .testnet)
    }
    
    static func getMasterEd25519KeyFromWord(_ m: [String]) -> Ed25519Key? {
        let hmac = HMAC(key: Array("ed25519 seed".utf8), variant: .sha512)
        guard let sum = try? hmac.authenticate(Mnemonic.seed(mnemonic: m).dataToHexString().hex2Bytes) else { return nil}
        return Ed25519Key(key: Array(sum[0..<32]), chainCode: Array(sum[32..<64]))
    }
    

    static func getHDKeyFromWords(mnemonic m: [String], path p:UInt32, chain c:ChainType) -> HDPrivateKey {
        let masterKey = getMasterKeyFromWords(m)
        if (c == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || c == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: p)
        } else if (c == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 714, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: p)
        } else {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: p)
        }
    }
    
    static func getPubToDpAddress(_ pubHex:String, _ chain:ChainType) -> String {
        var result = ""
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN || chain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            let sha256 = Crypto.sha256(Data.fromHex(pubHex)!)
            let ripemd160 = Crypto.ripemd160(sha256)
            
            if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
                result = try! SegwitAddrCoder.shared.encode2(hrp: "cosmos", program: ripemd160)
            } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                result = try! SegwitAddrCoder.shared.encode2(hrp: "iaa", program: ripemd160)
            } else if (chain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
                result = try! SegwitAddrCoder.shared.encode2(hrp: "bnb", program: ripemd160)
            }
            
        } else if (chain == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            let sha256 = Array(([UInt8](Crypto.sha256(Data.fromHex(pubHex)!)))[0..<20])
            result = try! SegwitAddrCoder.shared.encode2(hrp: "iov", program: Data(bytes: sha256))
        }
        return result;
    }

    static func getHDKeyDpAddressWithPath(_ masterKey:HDPrivateKey, path:Int, chain:ChainType) -> String {
        do {
            var childKey:HDPrivateKey?
            if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN || chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))
            } else if (chain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 714, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))
            } else {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))
            }
            return getPubToDpAddress(childKey!.privateKey().publicKey().raw.dataToHexString(), chain)
        } catch {
            return ""
        }
    }
    
    static func getDpAddressPath(_ mnemonic: [String], _ path:Int, _ chain:ChainType) -> String {
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN ||
                chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN || chain == ChainType.SUPPORT_CHAIN_BINANCE_MAIN) {
            //using Secp256k1
            let maskerKey = getMasterKeyFromWords(mnemonic)
            return WKey.getHDKeyDpAddressWithPath(maskerKey, path: path, chain: chain)
            
        } else if (chain == ChainType.SUPPORT_CHAIN_IOV_MAIN) {
            //using ed25519
            
            let path = IOV_BASE_PATH.appending(String(path)).appending("'")
            let cKey = deriveForPath(path, mnemonic)
            
            let pre: [UInt8] = Array("sigs/ed25519/".utf8)
            let post: [UInt8] = Ed25519.calcPublicKey(secretKey: cKey!.key)
            let result = pre + post
            
            return getPubToDpAddress(result.toHexString(), chain)
            
        }
        return ""
    }
    
    static func isEd25519ValidPath(path: String) -> Bool {
        guard let regex = try? NSRegularExpression(pattern: "^m(\\/[0-9]+')+$", options: .caseInsensitive) else { fatalError() }
        let matches = regex.matches(in: path, options: [], range: NSRange(location: 0, length: path.count))
        return matches.count > 0
    }
    
    static func deriveForPath(_ path: String, _ m: [String]) -> Ed25519Key? {
        guard isEd25519ValidPath(path: path) else { return nil }
        var key = getMasterEd25519KeyFromWord(m)
        let segments = path.components(separatedBy: "/")
        for segment in segments[1...] {
            guard var i = UInt32(segment.replacingOccurrences(of: "'", with: "")) else { return nil}
            i = i + (0b1 << 31)
            guard let k = key else { return nil }
            key = k.derive(index: i)
        }
        return key
    }
    

//    static func getCosmosDpAddress(key hdKey:HDPrivateKey) -> String {
//        let sha256 = Crypto.sha256(hdKey.privateKey().publicKey().raw)
//        let ripemd160 = Crypto.ripemd160(sha256)
//        return try! SegwitAddrCoder.shared.encode2(hrp: "cosmos", program: ripemd160)
//    }
//
//    static func getCosmosDpAddressWithPath(_ masterKey:HDPrivateKey, _ path:Int) -> String {
//        do {
//            let childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))
//            let sha256 = Crypto.sha256(childKey.privateKey().publicKey().raw)
//            let ripemd160 = Crypto.ripemd160(sha256)
//            return try! SegwitAddrCoder.shared.encode2(hrp: "cosmos", program: ripemd160)
//
//        } catch {
//            return ""
//        }
//    }
    
    
    static func isValidateAddress(_ address:String) -> Bool {
        if(address.count != 45 && !address.starts(with: "cosmos")) {
            return false
        }
        
        let bech32 = Bech32()
        guard let _ = try? bech32.decode(address) else {
            return false
        }
        return true
    }
    
    static func isValidateBech32(_ address:String) -> Bool {
        let bech32 = Bech32()
        guard let _ = try? bech32.decode(address) else {
            return false
        }
        return true
    }
    
//    static func getCosmosAddressFromPubKey(_ pubkey:String) -> String {
//        var result = ""
//        let bech32 = Bech32()
//        do {
//            guard let (_, data) = try? bech32.decode(pubkey) else {
//                return result
//            }
//            let converted = try convertBits(from: 5, to: 8, pad: false, idata: data)
//            let convertedhex = converted.hexEncodedString().replacingOccurrences(of: "eb5ae98721", with: "")
//            let sha256 = Crypto.sha256(dataWithHexString(hex: convertedhex))
//            let ripemd160 = Crypto.ripemd160(sha256)
//            result = try! SegwitAddrCoder.shared.encode2(hrp: "cosmos", program: ripemd160)
//        } catch {
//            print(error)
//        }
//        return result;
//    }
//    
//    
//    static func getCosmosAddressFromOpAddress(_ opAddress:String) -> String{
//        var result = ""
//        let bech32 = Bech32()
//        guard let (_, data) = try? bech32.decode(opAddress) else {
//            return result
//        }
//        result = bech32.encode("cosmos", values: data)
//        return result
//    }
    
    static func getAddressFromOpAddress(_ opAddress:String, _ chain:ChainType) -> String{
        var result = ""
        let bech32 = Bech32()
        guard let (_, data) = try? bech32.decode(opAddress) else {
            return result
        }
        if (chain == ChainType.SUPPORT_CHAIN_COSMOS_MAIN) {
            result = bech32.encode("cosmos", values: data)
        } else if (chain == ChainType.SUPPORT_CHAIN_IRIS_MAIN) {
            result = bech32.encode("iaa", values: data)
        }
        return result
    }
    
    
    static func convertBits(from: Int, to: Int, pad: Bool, idata: Data) throws -> Data {
        var acc: Int = 0
        var bits: Int = 0
        let maxv: Int = (1 << to) - 1
        let maxAcc: Int = (1 << (from + to - 1)) - 1
        var odata = Data()
        for ibyte in idata {
            acc = ((acc << from) | Int(ibyte)) & maxAcc
            bits += from
            while bits >= to {
                bits -= to
                odata.append(UInt8((acc >> bits) & maxv))
            }
        }
        if pad {
            if bits != 0 {
                odata.append(UInt8((acc << (to - bits)) & maxv))
            }
        } else if (bits >= from || ((acc << (to - bits)) & maxv) != 0) {
            print("error")
        }
        return odata
    }
    
    
    static func convertSignature(_ data: Data) -> String {
        var result = ""
        var subDataR: Data!
        
        if(data.count == 69) {
            let rangeR = Range(4..<36)
            subDataR = data.subdata(in: rangeR)
            
            let rangeL = Range(37..<69)
            let subDataL = data.subdata(in: rangeL)
            subDataR.append(subDataL)
            result = subDataR.hexEncodedString()
            
        } else if (data.count == 70) {
            let rangeR = Range(4..<36)
            subDataR = data.subdata(in: rangeR)
            
            let rangeL = Range(38..<70)
            let subDataL = data.subdata(in: rangeL)
            subDataR.append(subDataL)
            result = subDataR.hexEncodedString()
            
        } else if (data.count == 71) {
            let rangeR = Range(5..<37)
            subDataR = data.subdata(in: rangeR)
            
            let rangeL = Range(39..<71)
            let subDataL = data.subdata(in: rangeL)
            subDataR.append(subDataL)
            result = subDataR.hexEncodedString()
            
        } else {
        }
        
        result = subDataR.base64EncodedString()
        
        return result
    }
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static var english: [String.SubSequence] = {
        let words =
        """
            abandon
            ability
            able
            about
            above
            absent
            absorb
            abstract
            absurd
            abuse
            access
            accident
            account
            accuse
            achieve
            acid
            acoustic
            acquire
            across
            act
            action
            actor
            actress
            actual
            adapt
            add
            addict
            address
            adjust
            admit
            adult
            advance
            advice
            aerobic
            affair
            afford
            afraid
            again
            age
            agent
            agree
            ahead
            aim
            air
            airport
            aisle
            alarm
            album
            alcohol
            alert
            alien
            all
            alley
            allow
            almost
            alone
            alpha
            already
            also
            alter
            always
            amateur
            amazing
            among
            amount
            amused
            analyst
            anchor
            ancient
            anger
            angle
            angry
            animal
            ankle
            announce
            annual
            another
            answer
            antenna
            antique
            anxiety
            any
            apart
            apology
            appear
            apple
            approve
            april
            arch
            arctic
            area
            arena
            argue
            arm
            armed
            armor
            army
            around
            arrange
            arrest
            arrive
            arrow
            art
            artefact
            artist
            artwork
            ask
            aspect
            assault
            asset
            assist
            assume
            asthma
            athlete
            atom
            attack
            attend
            attitude
            attract
            auction
            audit
            august
            aunt
            author
            auto
            autumn
            average
            avocado
            avoid
            awake
            aware
            away
            awesome
            awful
            awkward
            axis
            baby
            bachelor
            bacon
            badge
            bag
            balance
            balcony
            ball
            bamboo
            banana
            banner
            bar
            barely
            bargain
            barrel
            base
            basic
            basket
            battle
            beach
            bean
            beauty
            because
            become
            beef
            before
            begin
            behave
            behind
            believe
            below
            belt
            bench
            benefit
            best
            betray
            better
            between
            beyond
            bicycle
            bid
            bike
            bind
            biology
            bird
            birth
            bitter
            black
            blade
            blame
            blanket
            blast
            bleak
            bless
            blind
            blood
            blossom
            blouse
            blue
            blur
            blush
            board
            boat
            body
            boil
            bomb
            bone
            bonus
            book
            boost
            border
            boring
            borrow
            boss
            bottom
            bounce
            box
            boy
            bracket
            brain
            brand
            brass
            brave
            bread
            breeze
            brick
            bridge
            brief
            bright
            bring
            brisk
            broccoli
            broken
            bronze
            broom
            brother
            brown
            brush
            bubble
            buddy
            budget
            buffalo
            build
            bulb
            bulk
            bullet
            bundle
            bunker
            burden
            burger
            burst
            bus
            business
            busy
            butter
            buyer
            buzz
            cabbage
            cabin
            cable
            cactus
            cage
            cake
            call
            calm
            camera
            camp
            can
            canal
            cancel
            candy
            cannon
            canoe
            canvas
            canyon
            capable
            capital
            captain
            car
            carbon
            card
            cargo
            carpet
            carry
            cart
            case
            cash
            casino
            castle
            casual
            cat
            catalog
            catch
            category
            cattle
            caught
            cause
            caution
            cave
            ceiling
            celery
            cement
            census
            century
            cereal
            certain
            chair
            chalk
            champion
            change
            chaos
            chapter
            charge
            chase
            chat
            cheap
            check
            cheese
            chef
            cherry
            chest
            chicken
            chief
            child
            chimney
            choice
            choose
            chronic
            chuckle
            chunk
            churn
            cigar
            cinnamon
            circle
            citizen
            city
            civil
            claim
            clap
            clarify
            claw
            clay
            clean
            clerk
            clever
            click
            client
            cliff
            climb
            clinic
            clip
            clock
            clog
            close
            cloth
            cloud
            clown
            club
            clump
            cluster
            clutch
            coach
            coast
            coconut
            code
            coffee
            coil
            coin
            collect
            color
            column
            combine
            come
            comfort
            comic
            common
            company
            concert
            conduct
            confirm
            congress
            connect
            consider
            control
            convince
            cook
            cool
            copper
            copy
            coral
            core
            corn
            correct
            cost
            cotton
            couch
            country
            couple
            course
            cousin
            cover
            coyote
            crack
            cradle
            craft
            cram
            crane
            crash
            crater
            crawl
            crazy
            cream
            credit
            creek
            crew
            cricket
            crime
            crisp
            critic
            crop
            cross
            crouch
            crowd
            crucial
            cruel
            cruise
            crumble
            crunch
            crush
            cry
            crystal
            cube
            culture
            cup
            cupboard
            curious
            current
            curtain
            curve
            cushion
            custom
            cute
            cycle
            dad
            damage
            damp
            dance
            danger
            daring
            dash
            daughter
            dawn
            day
            deal
            debate
            debris
            decade
            december
            decide
            decline
            decorate
            decrease
            deer
            defense
            define
            defy
            degree
            delay
            deliver
            demand
            demise
            denial
            dentist
            deny
            depart
            depend
            deposit
            depth
            deputy
            derive
            describe
            desert
            design
            desk
            despair
            destroy
            detail
            detect
            develop
            device
            devote
            diagram
            dial
            diamond
            diary
            dice
            diesel
            diet
            differ
            digital
            dignity
            dilemma
            dinner
            dinosaur
            direct
            dirt
            disagree
            discover
            disease
            dish
            dismiss
            disorder
            display
            distance
            divert
            divide
            divorce
            dizzy
            doctor
            document
            dog
            doll
            dolphin
            domain
            donate
            donkey
            donor
            door
            dose
            double
            dove
            draft
            dragon
            drama
            drastic
            draw
            dream
            dress
            drift
            drill
            drink
            drip
            drive
            drop
            drum
            dry
            duck
            dumb
            dune
            during
            dust
            dutch
            duty
            dwarf
            dynamic
            eager
            eagle
            early
            earn
            earth
            easily
            east
            easy
            echo
            ecology
            economy
            edge
            edit
            educate
            effort
            egg
            eight
            either
            elbow
            elder
            electric
            elegant
            element
            elephant
            elevator
            elite
            else
            embark
            embody
            embrace
            emerge
            emotion
            employ
            empower
            empty
            enable
            enact
            end
            endless
            endorse
            enemy
            energy
            enforce
            engage
            engine
            enhance
            enjoy
            enlist
            enough
            enrich
            enroll
            ensure
            enter
            entire
            entry
            envelope
            episode
            equal
            equip
            era
            erase
            erode
            erosion
            error
            erupt
            escape
            essay
            essence
            estate
            eternal
            ethics
            evidence
            evil
            evoke
            evolve
            exact
            example
            excess
            exchange
            excite
            exclude
            excuse
            execute
            exercise
            exhaust
            exhibit
            exile
            exist
            exit
            exotic
            expand
            expect
            expire
            explain
            expose
            express
            extend
            extra
            eye
            eyebrow
            fabric
            face
            faculty
            fade
            faint
            faith
            fall
            false
            fame
            family
            famous
            fan
            fancy
            fantasy
            farm
            fashion
            fat
            fatal
            father
            fatigue
            fault
            favorite
            feature
            february
            federal
            fee
            feed
            feel
            female
            fence
            festival
            fetch
            fever
            few
            fiber
            fiction
            field
            figure
            file
            film
            filter
            final
            find
            fine
            finger
            finish
            fire
            firm
            first
            fiscal
            fish
            fit
            fitness
            fix
            flag
            flame
            flash
            flat
            flavor
            flee
            flight
            flip
            float
            flock
            floor
            flower
            fluid
            flush
            fly
            foam
            focus
            fog
            foil
            fold
            follow
            food
            foot
            force
            forest
            forget
            fork
            fortune
            forum
            forward
            fossil
            foster
            found
            fox
            fragile
            frame
            frequent
            fresh
            friend
            fringe
            frog
            front
            frost
            frown
            frozen
            fruit
            fuel
            fun
            funny
            furnace
            fury
            future
            gadget
            gain
            galaxy
            gallery
            game
            gap
            garage
            garbage
            garden
            garlic
            garment
            gas
            gasp
            gate
            gather
            gauge
            gaze
            general
            genius
            genre
            gentle
            genuine
            gesture
            ghost
            giant
            gift
            giggle
            ginger
            giraffe
            girl
            give
            glad
            glance
            glare
            glass
            glide
            glimpse
            globe
            gloom
            glory
            glove
            glow
            glue
            goat
            goddess
            gold
            good
            goose
            gorilla
            gospel
            gossip
            govern
            gown
            grab
            grace
            grain
            grant
            grape
            grass
            gravity
            great
            green
            grid
            grief
            grit
            grocery
            group
            grow
            grunt
            guard
            guess
            guide
            guilt
            guitar
            gun
            gym
            habit
            hair
            half
            hammer
            hamster
            hand
            happy
            harbor
            hard
            harsh
            harvest
            hat
            have
            hawk
            hazard
            head
            health
            heart
            heavy
            hedgehog
            height
            hello
            helmet
            help
            hen
            hero
            hidden
            high
            hill
            hint
            hip
            hire
            history
            hobby
            hockey
            hold
            hole
            holiday
            hollow
            home
            honey
            hood
            hope
            horn
            horror
            horse
            hospital
            host
            hotel
            hour
            hover
            hub
            huge
            human
            humble
            humor
            hundred
            hungry
            hunt
            hurdle
            hurry
            hurt
            husband
            hybrid
            ice
            icon
            idea
            identify
            idle
            ignore
            ill
            illegal
            illness
            image
            imitate
            immense
            immune
            impact
            impose
            improve
            impulse
            inch
            include
            income
            increase
            index
            indicate
            indoor
            industry
            infant
            inflict
            inform
            inhale
            inherit
            initial
            inject
            injury
            inmate
            inner
            innocent
            input
            inquiry
            insane
            insect
            inside
            inspire
            install
            intact
            interest
            into
            invest
            invite
            involve
            iron
            island
            isolate
            issue
            item
            ivory
            jacket
            jaguar
            jar
            jazz
            jealous
            jeans
            jelly
            jewel
            job
            join
            joke
            journey
            joy
            judge
            juice
            jump
            jungle
            junior
            junk
            just
            kangaroo
            keen
            keep
            ketchup
            key
            kick
            kid
            kidney
            kind
            kingdom
            kiss
            kit
            kitchen
            kite
            kitten
            kiwi
            knee
            knife
            knock
            know
            lab
            label
            labor
            ladder
            lady
            lake
            lamp
            language
            laptop
            large
            later
            latin
            laugh
            laundry
            lava
            law
            lawn
            lawsuit
            layer
            lazy
            leader
            leaf
            learn
            leave
            lecture
            left
            leg
            legal
            legend
            leisure
            lemon
            lend
            length
            lens
            leopard
            lesson
            letter
            level
            liar
            liberty
            library
            license
            life
            lift
            light
            like
            limb
            limit
            link
            lion
            liquid
            list
            little
            live
            lizard
            load
            loan
            lobster
            local
            lock
            logic
            lonely
            long
            loop
            lottery
            loud
            lounge
            love
            loyal
            lucky
            luggage
            lumber
            lunar
            lunch
            luxury
            lyrics
            machine
            mad
            magic
            magnet
            maid
            mail
            main
            major
            make
            mammal
            man
            manage
            mandate
            mango
            mansion
            manual
            maple
            marble
            march
            margin
            marine
            market
            marriage
            mask
            mass
            master
            match
            material
            math
            matrix
            matter
            maximum
            maze
            meadow
            mean
            measure
            meat
            mechanic
            medal
            media
            melody
            melt
            member
            memory
            mention
            menu
            mercy
            merge
            merit
            merry
            mesh
            message
            metal
            method
            middle
            midnight
            milk
            million
            mimic
            mind
            minimum
            minor
            minute
            miracle
            mirror
            misery
            miss
            mistake
            mix
            mixed
            mixture
            mobile
            model
            modify
            mom
            moment
            monitor
            monkey
            monster
            month
            moon
            moral
            more
            morning
            mosquito
            mother
            motion
            motor
            mountain
            mouse
            move
            movie
            much
            muffin
            mule
            multiply
            muscle
            museum
            mushroom
            music
            must
            mutual
            myself
            mystery
            myth
            naive
            name
            napkin
            narrow
            nasty
            nation
            nature
            near
            neck
            need
            negative
            neglect
            neither
            nephew
            nerve
            nest
            net
            network
            neutral
            never
            news
            next
            nice
            night
            noble
            noise
            nominee
            noodle
            normal
            north
            nose
            notable
            note
            nothing
            notice
            novel
            now
            nuclear
            number
            nurse
            nut
            oak
            obey
            object
            oblige
            obscure
            observe
            obtain
            obvious
            occur
            ocean
            october
            odor
            off
            offer
            office
            often
            oil
            okay
            old
            olive
            olympic
            omit
            once
            one
            onion
            online
            only
            open
            opera
            opinion
            oppose
            option
            orange
            orbit
            orchard
            order
            ordinary
            organ
            orient
            original
            orphan
            ostrich
            other
            outdoor
            outer
            output
            outside
            oval
            oven
            over
            own
            owner
            oxygen
            oyster
            ozone
            pact
            paddle
            page
            pair
            palace
            palm
            panda
            panel
            panic
            panther
            paper
            parade
            parent
            park
            parrot
            party
            pass
            patch
            path
            patient
            patrol
            pattern
            pause
            pave
            payment
            peace
            peanut
            pear
            peasant
            pelican
            pen
            penalty
            pencil
            people
            pepper
            perfect
            permit
            person
            pet
            phone
            photo
            phrase
            physical
            piano
            picnic
            picture
            piece
            pig
            pigeon
            pill
            pilot
            pink
            pioneer
            pipe
            pistol
            pitch
            pizza
            place
            planet
            plastic
            plate
            play
            please
            pledge
            pluck
            plug
            plunge
            poem
            poet
            point
            polar
            pole
            police
            pond
            pony
            pool
            popular
            portion
            position
            possible
            post
            potato
            pottery
            poverty
            powder
            power
            practice
            praise
            predict
            prefer
            prepare
            present
            pretty
            prevent
            price
            pride
            primary
            print
            priority
            prison
            private
            prize
            problem
            process
            produce
            profit
            program
            project
            promote
            proof
            property
            prosper
            protect
            proud
            provide
            public
            pudding
            pull
            pulp
            pulse
            pumpkin
            punch
            pupil
            puppy
            purchase
            purity
            purpose
            purse
            push
            put
            puzzle
            pyramid
            quality
            quantum
            quarter
            question
            quick
            quit
            quiz
            quote
            rabbit
            raccoon
            race
            rack
            radar
            radio
            rail
            rain
            raise
            rally
            ramp
            ranch
            random
            range
            rapid
            rare
            rate
            rather
            raven
            raw
            razor
            ready
            real
            reason
            rebel
            rebuild
            recall
            receive
            recipe
            record
            recycle
            reduce
            reflect
            reform
            refuse
            region
            regret
            regular
            reject
            relax
            release
            relief
            rely
            remain
            remember
            remind
            remove
            render
            renew
            rent
            reopen
            repair
            repeat
            replace
            report
            require
            rescue
            resemble
            resist
            resource
            response
            result
            retire
            retreat
            return
            reunion
            reveal
            review
            reward
            rhythm
            rib
            ribbon
            rice
            rich
            ride
            ridge
            rifle
            right
            rigid
            ring
            riot
            ripple
            risk
            ritual
            rival
            river
            road
            roast
            robot
            robust
            rocket
            romance
            roof
            rookie
            room
            rose
            rotate
            rough
            round
            route
            royal
            rubber
            rude
            rug
            rule
            run
            runway
            rural
            sad
            saddle
            sadness
            safe
            sail
            salad
            salmon
            salon
            salt
            salute
            same
            sample
            sand
            satisfy
            satoshi
            sauce
            sausage
            save
            say
            scale
            scan
            scare
            scatter
            scene
            scheme
            school
            science
            scissors
            scorpion
            scout
            scrap
            screen
            script
            scrub
            sea
            search
            season
            seat
            second
            secret
            section
            security
            seed
            seek
            segment
            select
            sell
            seminar
            senior
            sense
            sentence
            series
            service
            session
            settle
            setup
            seven
            shadow
            shaft
            shallow
            share
            shed
            shell
            sheriff
            shield
            shift
            shine
            ship
            shiver
            shock
            shoe
            shoot
            shop
            short
            shoulder
            shove
            shrimp
            shrug
            shuffle
            shy
            sibling
            sick
            side
            siege
            sight
            sign
            silent
            silk
            silly
            silver
            similar
            simple
            since
            sing
            siren
            sister
            situate
            six
            size
            skate
            sketch
            ski
            skill
            skin
            skirt
            skull
            slab
            slam
            sleep
            slender
            slice
            slide
            slight
            slim
            slogan
            slot
            slow
            slush
            small
            smart
            smile
            smoke
            smooth
            snack
            snake
            snap
            sniff
            snow
            soap
            soccer
            social
            sock
            soda
            soft
            solar
            soldier
            solid
            solution
            solve
            someone
            song
            soon
            sorry
            sort
            soul
            sound
            soup
            source
            south
            space
            spare
            spatial
            spawn
            speak
            special
            speed
            spell
            spend
            sphere
            spice
            spider
            spike
            spin
            spirit
            split
            spoil
            sponsor
            spoon
            sport
            spot
            spray
            spread
            spring
            spy
            square
            squeeze
            squirrel
            stable
            stadium
            staff
            stage
            stairs
            stamp
            stand
            start
            state
            stay
            steak
            steel
            stem
            step
            stereo
            stick
            still
            sting
            stock
            stomach
            stone
            stool
            story
            stove
            strategy
            street
            strike
            strong
            struggle
            student
            stuff
            stumble
            style
            subject
            submit
            subway
            success
            such
            sudden
            suffer
            sugar
            suggest
            suit
            summer
            sun
            sunny
            sunset
            super
            supply
            supreme
            sure
            surface
            surge
            surprise
            surround
            survey
            suspect
            sustain
            swallow
            swamp
            swap
            swarm
            swear
            sweet
            swift
            swim
            swing
            switch
            sword
            symbol
            symptom
            syrup
            system
            table
            tackle
            tag
            tail
            talent
            talk
            tank
            tape
            target
            task
            taste
            tattoo
            taxi
            teach
            team
            tell
            ten
            tenant
            tennis
            tent
            term
            test
            text
            thank
            that
            theme
            then
            theory
            there
            they
            thing
            this
            thought
            three
            thrive
            throw
            thumb
            thunder
            ticket
            tide
            tiger
            tilt
            timber
            time
            tiny
            tip
            tired
            tissue
            title
            toast
            tobacco
            today
            toddler
            toe
            together
            toilet
            token
            tomato
            tomorrow
            tone
            tongue
            tonight
            tool
            tooth
            top
            topic
            topple
            torch
            tornado
            tortoise
            toss
            total
            tourist
            toward
            tower
            town
            toy
            track
            trade
            traffic
            tragic
            train
            transfer
            trap
            trash
            travel
            tray
            treat
            tree
            trend
            trial
            tribe
            trick
            trigger
            trim
            trip
            trophy
            trouble
            truck
            true
            truly
            trumpet
            trust
            truth
            try
            tube
            tuition
            tumble
            tuna
            tunnel
            turkey
            turn
            turtle
            twelve
            twenty
            twice
            twin
            twist
            two
            type
            typical
            ugly
            umbrella
            unable
            unaware
            uncle
            uncover
            under
            undo
            unfair
            unfold
            unhappy
            uniform
            unique
            unit
            universe
            unknown
            unlock
            until
            unusual
            unveil
            update
            upgrade
            uphold
            upon
            upper
            upset
            urban
            urge
            usage
            use
            used
            useful
            useless
            usual
            utility
            vacant
            vacuum
            vague
            valid
            valley
            valve
            van
            vanish
            vapor
            various
            vast
            vault
            vehicle
            velvet
            vendor
            venture
            venue
            verb
            verify
            version
            very
            vessel
            veteran
            viable
            vibrant
            vicious
            victory
            video
            view
            village
            vintage
            violin
            virtual
            virus
            visa
            visit
            visual
            vital
            vivid
            vocal
            voice
            void
            volcano
            volume
            vote
            voyage
            wage
            wagon
            wait
            walk
            wall
            walnut
            want
            warfare
            warm
            warrior
            wash
            wasp
            waste
            water
            wave
            way
            wealth
            weapon
            wear
            weasel
            weather
            web
            wedding
            weekend
            weird
            welcome
            west
            wet
            whale
            what
            wheat
            wheel
            when
            where
            whip
            whisper
            wide
            width
            wife
            wild
            will
            win
            window
            wine
            wing
            wink
            winner
            winter
            wire
            wisdom
            wise
            wish
            witness
            wolf
            woman
            wonder
            wood
            wool
            word
            work
            world
            worry
            worth
            wrap
            wreck
            wrestle
            wrist
            write
            wrong
            yard
            year
            yellow
            you
            young
            youth
            zebra
            zero
            zone
            zoo
            """
        return words.split(separator: "\n")
    }()
}


extension Data {
    var bytes : [UInt8]{
        return [UInt8](self)
    }
}
