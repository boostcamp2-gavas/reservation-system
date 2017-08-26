package com.gavas.service.serviceImpl;

import com.gavas.dao.ProductPriceDao;
import com.gavas.dao.ReservationDao;
import com.gavas.domain.ProductPrice;
import com.gavas.domain.Reservation;
import com.gavas.domain.dto.ReservationDto;
import com.gavas.service.ProductPriceService;
import com.gavas.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.peer.SystemTrayPeer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationDao reservationDao;
    private ProductPriceService productPriceService;

    @Autowired
    public ReservationServiceImpl(ReservationDao reservationDao, ProductPriceService productPriceService) {
        this.reservationDao = reservationDao;
        this.productPriceService = productPriceService;
    }

    @Transactional
    @Override
    public Integer addReservation(Reservation reservation) {
        Date currentDate = new Date();
        reservation.setCreateDate(currentDate);
        reservation.setModifyDate(currentDate);
        return reservationDao.insertReservation(reservation);
    }

    @Transactional
    @Override
    public Integer updateReservationType(Reservation reservation) {
        reservation.setModifyDate(new Date());
        return reservationDao.updateReservation(reservation);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReservationDto> getReservationList(Long id) {
        List<ReservationDto> reservationDtoList = reservationDao.selectReservationListByUserId(id);
        setTotalPrice(reservationDtoList);
        return reservationDtoList;
    }

    private void setTotalPrice(List<ReservationDto> reservationDtoList) {
        Map<Long, List<ProductPrice>> productPriceMap = new HashMap<>();

        for (ReservationDto reservationDto : reservationDtoList) {
            Double totalPrice = 0D;
            Long productId = reservationDto.getProductId();

            if (productPriceMap.get(productId) == null) {
                productPriceMap.put(productId, productPriceService.getProductPriceList(productId));
            }
            List<ProductPrice> productPriceList = productPriceMap.get(productId);

            for (ProductPrice productPrice : productPriceList) {
                Long count = 0L;

                if (productPrice.getPriceType().equals(1L) && reservationDto.getGeneralTicketCount() != null) {
                    count = reservationDto.getGeneralTicketCount();
                } else if (productPrice.getPriceType().equals(2L) && reservationDto.getYouthTicketCount() != null) {
                    count = reservationDto.getYouthTicketCount();
                } else if (productPrice.getPriceType().equals(3L) && reservationDto.getChildTicketCount() != null) {
                    count = reservationDto.getChildTicketCount();
                }

                Double discountRate = productPrice.getDiscountRate().doubleValue();
                totalPrice += (productPrice.getPrice() * (1 - discountRate)) * count;
            }

            reservationDto.setTotalPrice(totalPrice.longValue());
        }
    }
}
